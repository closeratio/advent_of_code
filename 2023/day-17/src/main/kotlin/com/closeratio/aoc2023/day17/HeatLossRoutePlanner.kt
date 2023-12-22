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

        val openPaths = PriorityQueue(Comparator.comparing(Path::heatLossTotal))
        openPaths.add(
            Path(linkedSetOf(ZERO), heatmap.getValue(ZERO))
        )

        val bestMap = mutableMapOf<SearchState, Long>()

        while (openPaths.isNotEmpty()) {
            val path = openPaths.poll()
            val searchState = path.searchState(heatmap)
            if (bestMap.getOrDefault(searchState, Long.MAX_VALUE) < path.heatLossTotal) {
                continue
            }
            bestMap[searchState] = path.heatLossTotal

            if (path.positions.last() == goal) {
                return path.heatLossTotal
            }

            openPaths += path.calculateNextPaths(heatmap)
        }

        throw IllegalStateException("Unable to find path")
    }

}

