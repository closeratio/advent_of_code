package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
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
        val openPaths = PriorityQueue(Comparator.comparing(Path::heatLossTotal))
        openPaths.addAll(
            Path(
                ZERO,
                emptyMap(),
                0
            ).calculateNextPaths(heatmap, bestMap)
        )

        while (openPaths.isNotEmpty()) {
            val path = openPaths.poll()
            val move = path.moves.values.last()
            if (bestMap.getOrDefault(move, Long.MAX_VALUE) < path.heatLossTotal) {
                continue
            }

            bestMap[move] = path.heatLossTotal

            if (move.pos == goal) {
                return path.heatLossTotal
            }

            openPaths += path.calculateNextPaths(heatmap, bestMap)
        }

        throw IllegalStateException("Unable to find path")
    }

}

