package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day17.Direction.*
import kotlin.Long.Companion.MAX_VALUE

class Move(
    val pos: Vec2,
    val direction: Direction,
    val directionCount: Long,
    val heatLossTotal: Long
) {

    fun next(
        heatmap: Heatmap,
        bestMap: Map<Move, Long>,
        visited: Set<Move>,
        minConsecutive: Long,
        maxConsecutive: Long
    ): List<Move> = when (direction) {
        UP -> if (directionCount >= minConsecutive) listOf(LEFT, UP, RIGHT) else listOf(UP)
        RIGHT -> if (directionCount >= minConsecutive) listOf(UP, RIGHT, DOWN) else listOf(RIGHT)
        DOWN -> if (directionCount >= minConsecutive) listOf(RIGHT, DOWN, LEFT) else listOf(DOWN)
        LEFT -> if (directionCount >= minConsecutive) listOf(DOWN, LEFT, UP) else listOf(LEFT)
    }
        .map { newDirection ->
            val newPos = when (newDirection) {
                LEFT -> pos.left()
                RIGHT -> pos.right()
                UP -> pos.up()
                DOWN -> pos.down()
            }

            Move(
                newPos,
                newDirection,
                if (direction == newDirection) directionCount + 1 else 1,
                heatLossTotal + heatmap.getOrDefault(newPos, MAX_VALUE)
            )
        }
        .filter { it.directionCount <= maxConsecutive }
        .filter { it.pos in heatmap }
        .filter { it.heatLossTotal < bestMap.getOrDefault(it, MAX_VALUE) }
        .filter { it !in visited }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Move

        if (pos != other.pos) return false
        if (direction != other.direction) return false
        if (directionCount != other.directionCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pos.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + directionCount.hashCode()
        return result
    }

    override fun toString(): String {
        return "Move(pos=$pos, direction=$direction, directionCount=$directionCount, heatLossTotal=$heatLossTotal)"
    }

    fun satisfiesConstraints(maxConsecutive: Long, minConsecutive: Long): Boolean =
        directionCount in minConsecutive..maxConsecutive


}
