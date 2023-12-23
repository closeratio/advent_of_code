package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import com.closeratio.aoc2023.day17.Direction.RIGHT
import com.closeratio.aoc2023.day17.Direction.UP
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

        var closest = ZERO.manhattanDistance(goal)

        while (openMoves.isNotEmpty()) {
            val move = openMoves.poll()
            if (bestMap.getOrDefault(move, Long.MAX_VALUE) < move.heatLossTotal) {
                continue
            }
            closedMoves += move

            bestMap[move] = move.heatLossTotal

            if (move.pos == goal) {
                val route = arrayListOf(move)
                while (route.last().pos != ZERO) {
                    route += route.last().parent
                }
                val routeMap = route.associate { it.pos to it.direction }

                println((heatmap.minY..heatmap.maxY).joinToString("\n") { y ->
                    (heatmap.minX..heatmap.maxX).joinToString("") { x ->
                        val pos = Vec2(x, y)
                        if (pos in routeMap) {
                            when (routeMap.getValue(pos)) {
                                UP -> "^"
                                Direction.DOWN -> "v"
                                Direction.LEFT -> "<"
                                RIGHT -> ">"
                            }
                        } else {
                            heatmap.getValue(pos).toString()
                        }
                    }
                })

                return move.heatLossTotal
            }

            val dist = move.pos.manhattanDistance(goal)
            if (dist < closest) {
                closest = dist
                println("Closer: $closest")
            }

            openMoves += move.next(heatmap, bestMap, closedMoves)
        }

        throw IllegalStateException("Unable to find path")
    }

}

