package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import kotlin.Long.Companion.MAX_VALUE

class Move(
    val pos: Vec2,
    val direction: Direction,
    val directionCount: Long,
    val heatLossTotal: Long,
    val parent: Move?
) {

    fun next(
        heatmap: Heatmap,
        bestMap: Map<Move, Long>,
        visited: Set<Move>
    ): List<Move> = pos
        .immediatelyAdjacent()
        .map { newPos ->
            val newDirection = when (newPos) {
                pos.left() -> Direction.LEFT
                pos.right() -> Direction.RIGHT
                pos.up() -> Direction.UP
                pos.down() -> Direction.DOWN
                else -> throw IllegalArgumentException("Unknown transition: $pos -> $newPos")
            }

            Move(
                newPos,
                newDirection,
                if (direction == newDirection) directionCount + 1 else 1,
                heatLossTotal + heatmap.getOrDefault(newPos, MAX_VALUE),
                this
            )
        }
        .filter { it !in visited }
        .filter { it.pos in heatmap }
        .filter { it.heatLossTotal < bestMap.getOrDefault(it, MAX_VALUE) }
        .filter { it.directionCount < 4 }
        .filter { it.pos != it.parent?.parent?.pos } // Prevents "reversing"

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


}
