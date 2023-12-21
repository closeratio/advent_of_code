package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import org.springframework.stereotype.Component
import java.util.*

@Component
class HeatLossRoutePlanner {

    private fun parseHeatmap(
        lines: List<String>
    ): Heatmap = TODO()

    fun calculateMinimalHeatLoss(
        lines: List<String>
    ): Long {
        val heatmap = parseHeatmap(lines)
        val goal = Vec2(heatmap.maxX, heatmap.maxY)

        val closedPaths = mutableSetOf<Path>()
        val openPaths = PriorityQueue(Comparator.comparing(Path::heatLossTotal))
        openPaths.add(
            Path(linkedSetOf(ZERO), heatmap.getValue(ZERO))
        )

        while (openPaths.isNotEmpty()) {
            val path = openPaths.poll()
            closedPaths += path

            if (path.positions.last() == goal) {
                return path.heatLossTotal
            }

            openPaths += path.calculateNextPaths(heatmap)
        }

        throw IllegalStateException("Unable to find path")
    }

}

