package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day17.Direction.*
import kotlin.Long.Companion.MAX_VALUE

data class Path(
    val start: Vec2,
    val moves: Set<Move>,
    val heatLossTotal: Long
) {

    private val positions = linkedSetOf(start) + moves.map(Move::pos).toSet()
    val movesList = moves.toList()

    fun calculateNextPaths(heatmap: Heatmap): List<Path> = (positions.lastOrNull() ?: start)
        .immediatelyAdjacent()
        .filter { it !in positions }
        .map {
            val lastMove = movesList.lastOrNull()
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
                moves + Move(
                    it,
                    direction,
                    directionCount
                ),
                heatLossTotal + heatmap.getOrDefault(it, MAX_VALUE)
            )
        }
        .filter { it.isValid(heatmap) }

    private fun isValid(
        heatmap: Heatmap
    ): Boolean {
        if (movesList.last().pos.x !in heatmap.xRange || movesList.last().pos.y !in heatmap.yRange) {
            return false
        }

        if (moves.size < 3) {
            return true
        }

        return movesList.last().directionCount < 4
    }

}
