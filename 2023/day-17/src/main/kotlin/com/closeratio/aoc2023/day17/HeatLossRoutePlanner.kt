package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import com.closeratio.aoc2023.day17.Direction.*
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
        openPaths.addAll(
            Path(
                ZERO,
                emptySet(),
                0
            ).calculateNextPaths(heatmap)
        )

        val bestMap = mutableMapOf<Move, Long>()

        while (openPaths.isNotEmpty()) {
            val path = openPaths.poll()
            val move = path.movesList.last()
            if (bestMap.getOrDefault(move, Long.MAX_VALUE) < path.heatLossTotal) {
                continue
            }

            bestMap[move] = path.heatLossTotal

            if (path.moves.last().pos == goal) {
                val moveMap = path.moves.associateBy(Move::pos)

                println((heatmap.minY..heatmap.maxY).joinToString("\n") { y ->
                    (heatmap.minX..heatmap.maxX).joinToString("") { x ->
                        val pos = Vec2(x, y)
                        if (pos in moveMap) {
                            when (moveMap.getValue(pos).direction) {
                                UP -> "^"
                                DOWN -> "v"
                                LEFT -> "<"
                                RIGHT -> ">"
                            }
                        } else {
                            heatmap.getValue(pos).toString()
                        }
                    }
                })
                return path.heatLossTotal
            }

            openPaths += path.calculateNextPaths(heatmap)
        }

        throw IllegalStateException("Unable to find path")
    }

}

