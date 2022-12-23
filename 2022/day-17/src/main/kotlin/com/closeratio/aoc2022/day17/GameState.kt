package com.closeratio.aoc2022.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day17.Direction.LEFT
import com.closeratio.aoc2022.day17.Direction.RIGHT
import com.closeratio.aoc2022.day17.rockshape.*
import kotlin.math.absoluteValue

data class GameState(
    val rocks: Set<Vec2>,
    val shapeIndex: Int,
    val directionList: List<Direction>,
    val directionIndex: Int,
    val fallenRocks: Int
) {

    fun iterate(): GameState {
        var fallen = false
        var currDirectionIndex = directionIndex
        var shape = generateNewRockShape()
        while (!fallen) {
            shape = applyDirection(shape, currDirectionIndex)
            currDirectionIndex = (currDirectionIndex + 1) % directionList.size

            val downwardShape = shape.down()
            val downwardShapeMaxY = downwardShape.rocks.maxOf(Vec2::y)
            if (downwardShape.overlapsWith(rocks) || downwardShapeMaxY > 0) {
                fallen = true
            } else {
                shape = downwardShape
            }
        }

        return GameState(
            rocks + shape.rocks,
            (shapeIndex + 1) % 5,
            directionList,
            currDirectionIndex,
            fallenRocks + 1
        )
    }

    private fun applyDirection(
        shape: RockShape,
        currDirectionIndex: Int
    ): RockShape {
        val movedShape = when (directionList[currDirectionIndex]) {
            LEFT -> shape.left()
            RIGHT -> shape.right()
        }

        val minX = movedShape.rocks.minBy(Vec2::x).x
        val maxX = movedShape.rocks.maxBy(Vec2::x).x
        val overlaps = movedShape.overlapsWith(rocks)

        return when {
            minX < 0 || maxX >= 7 || overlaps -> shape
            else -> movedShape
        }
    }

    private fun generateNewRockShape(): RockShape {
        val shape = when (shapeIndex) {
            0 -> HorizontalLine()
            1 -> Cross()
            2 -> LShape()
            3 -> VerticalLine()
            4 -> Square()
            else -> throw IllegalArgumentException("Shape index is invalid value: $shapeIndex")
        }
        val highestRock = rocks.minOfOrNull(Vec2::y) ?: 1

        val offset = highestRock - 4
        val offsetRocks = shape.rocks.map { Vec2(it.x, it.y + offset) }.toSet()

        return shape.constructor(offsetRocks)
    }

    fun generateKey(): GameStateKey {

        return GameStateKey(
            rocks.groupBy(Vec2::x, Vec2::y)
                .mapValues { (_, v) -> v.min() }
                .entries
                .sortedBy(Map.Entry<Long, Long>::key)
                .map(Map.Entry<Long, Long>::value)
                .let { list ->
                    val maxY = list.max()
                    list.map { it - maxY }
                },
            shapeIndex,
            directionIndex
        )
    }

    fun height(): Long = rocks
        .minOf(Vec2::y)
        .absoluteValue + 1

}
