package com.closeratio.aoc2022.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day17.Direction.LEFT
import com.closeratio.aoc2022.day17.Direction.RIGHT
import com.closeratio.aoc2022.day17.rockshape.*

data class GameState(
    val shape: RockShape,
    val rocks: Set<Vec2>,
    val shapeIndex: Int,
    val directionList: List<Direction>,
    val directionIndex: Int,
    val fallenRocks: Int
) {
    private fun getRockShape(): RockShape = when (shapeIndex) {
        0 -> HorizontalLine()
        1 -> Cross()
        2 -> LShape()
        3 -> VerticalLine()
        4 -> Square()
        else -> throw IllegalArgumentException("Shape index is invalid value: $shapeIndex")
    }

    fun iterate(): GameState {
        val directionShape = applyDirection()
        val downwardShape = directionShape.down()
        val downwardShapeMaxY = downwardShape.rocks.maxOf(Vec2::y)

        return if (downwardShape.overlapsWith(rocks) || downwardShapeMaxY > 0) {
            val newRocks = rocks + directionShape.rocks

            GameState(
                generateNewRockShape(newRocks),
                newRocks,
                (shapeIndex + 1) % 5,
                directionList,
                (directionIndex + 1) % directionList.size,
                fallenRocks + 1
            )

        } else {
            GameState(
                downwardShape,
                rocks,
                shapeIndex,
                directionList,
                (directionIndex + 1) % directionList.size,
                fallenRocks
            )
        }
    }

    private fun applyDirection(): RockShape {
        val movedShape = when (directionList[directionIndex]) {
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

    private fun generateNewRockShape(existingRocks: Set<Vec2>): RockShape {
        val shape = getRockShape()
        val highestRock = existingRocks.minOf(Vec2::y)

        val offset = highestRock - 4
        val offsetRocks = shape.rocks.map { Vec2(it.x, it.y + offset) }.toSet()

        return shape.constructor(offsetRocks)
    }

    fun printString() {
        val minY = (rocks + shape.rocks).minOf(Vec2::y).toInt()

        val string = (minY..1).joinToString("\n", postfix = "\n") { y ->
            (-1..7).joinToString("") { x ->
                val vec = Vec2(x.toLong(), y.toLong())

                if ((x == -1 || x == 7) && y == 1) {
                    "+"
                } else if (x == -1 || x == 7) {
                    "|"
                } else if (y == 1) (
                        "-"
                        ) else if (vec in shape.rocks) {
                    "@"
                } else if (vec in rocks) {
                    "#"
                } else {
                    "."
                }
            }
        }

        println(string)
    }

}
