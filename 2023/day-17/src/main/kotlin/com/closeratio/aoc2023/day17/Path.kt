package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day17.Direction.*
import kotlin.Long.Companion.MAX_VALUE

data class Path(
    val start: Vec2,
    val moves: Map<Vec2, Move>,
    val heatLossTotal: Long
) {

    fun calculateNextPaths(
        heatmap: Heatmap,
        bestMap: Map<Move, Long>
    ): List<Path> = (moves.values.lastOrNull()?.pos ?: start)
        .immediatelyAdjacent()
        .filter { it !in moves }
        .map {
            val lastMove = moves.values.lastOrNull()
            val lastPos = lastMove?.pos ?: start
            val direction = when (it) {
                lastPos.left() -> LEFT
                lastPos.right() -> RIGHT
                lastPos.up() -> UP
                lastPos.down() -> DOWN
                else -> throw IllegalArgumentException("Unknown transition: $lastPos -> $it")
            }
            val directionCount = if (lastMove?.direction == direction) {
                lastMove.directionCount + 1
            } else {
                1
            }

            Path(
                start,
                moves + (it to Move(
                    it,
                    direction,
                    directionCount
                )),
                heatLossTotal + heatmap.getOrDefault(it, MAX_VALUE)
            )
        }
        .filter { it.isValid(heatmap, bestMap) }

    private fun isValid(
        heatmap: Heatmap,
        bestMap: Map<Move, Long>
    ): Boolean {
        val last = moves.values.last()
        if (last.pos.x !in heatmap.xRange || last.pos.y !in heatmap.yRange) {
            return false
        }

        if (moves.size < 3) {
            return true
        }

        if (last.directionCount > 3) {
            return false
        }

        return heatLossTotal < bestMap.getOrDefault(last, MAX_VALUE)
    }

}
