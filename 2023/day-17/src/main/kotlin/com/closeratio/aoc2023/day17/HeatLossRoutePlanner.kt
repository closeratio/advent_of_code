package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import com.closeratio.aoc2023.day17.Direction.RIGHT
import org.springframework.stereotype.Component
import java.util.*

@Component
class HeatLossRoutePlanner {

    private fun parseHeatmap(
        lines: List<String>
    ): Heatmap = lines
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char ->
                Vec2(x, y) to char.toString().toLong()
            }
        }
        .toMap()
        .let(::Heatmap)

    fun calculateMinimalHeatLoss(
        lines: List<String>
    ): Long {
        val heatmap = parseHeatmap(lines)
        val goal = Vec2(heatmap.maxX, heatmap.maxY)

        val bestMap = mutableMapOf<Move, Long>()
        val closedMoves = mutableSetOf<Move>()
        val openMoves = PriorityQueue(Comparator.comparing(Move::heatLossTotal))
        openMoves.addAll(
            Move(
                ZERO,
                RIGHT,
                0,
                0,
                null
            ).next(heatmap, bestMap, closedMoves)
        )

        while (openMoves.isNotEmpty()) {
            val move = openMoves.poll()
            if (bestMap.getOrDefault(move, Long.MAX_VALUE) < move.heatLossTotal) {
                continue
            }

            bestMap[move] = move.heatLossTotal

            if (move.pos == goal) {
                return move.heatLossTotal
            }

            val nextMoves = move.next(heatmap, bestMap, closedMoves)
            closedMoves += nextMoves
            openMoves += nextMoves
        }

        throw IllegalStateException("Unable to find path")
    }

}

