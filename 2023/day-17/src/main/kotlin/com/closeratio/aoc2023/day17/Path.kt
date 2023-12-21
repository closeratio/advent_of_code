package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day17.Direction.*

data class Path(
    val positions: Set<Vec2>,
    val heatLossTotal: Long
) {

    private val positionsList = positions.toList()

    fun calculateNextPaths(heatmap: Heatmap): List<Path> = positions
        .last()
        .immediatelyAdjacent()
        .filter { it !in positions }
        .filter { it.x in heatmap.minX..heatmap.minY && it.y in heatmap.minY..heatmap.maxY }
        .map {
            Path(
                positions + it,
                heatLossTotal + heatmap.getValue(it)
            )
        }
        .filter(Path::isValid)

    private fun isValid(): Boolean {
        if (positions.size <= 3) {
            return true
        }

        return positionsList
            .takeLast(4)
            .windowed(2)
            .map { (first, second) ->
                when {
                    first.left() == second -> LEFT
                    first.right() == second -> RIGHT
                    first.up() == second -> UP
                    first.down() == second -> DOWN
                    else -> throw IllegalArgumentException("Unknown transition: $first -> $second")
                }
            }
            .toSet()
            .size > 1
    }
}
