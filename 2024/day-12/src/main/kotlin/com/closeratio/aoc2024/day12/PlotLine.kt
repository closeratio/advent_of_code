package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day12.Orientation.HORIZONTAL
import com.closeratio.aoc2024.day12.Orientation.VERTICAL
import com.closeratio.aoc2024.day12.PlotLine.CheckType.FIRST
import com.closeratio.aoc2024.day12.PlotLine.CheckType.SECOND

data class PlotLine(
    val plots: Set<Plot>,
    val orientation: Orientation
) {
    enum class CheckType {
        FIRST,
        SECOND
    }

    fun calculateSides(
        regionPositions: Set<Vec2>
    ): List<Side> = calculateSpecificSides(regionPositions, FIRST) + calculateSpecificSides(regionPositions, SECOND)

    private fun calculateSpecificSides(
        regionPositions: Set<Vec2>,
        checkType: CheckType
    ): List<Side> {
        val sidePlots = plots
            .map(Plot::pos)
            .filter { isSide(it, regionPositions, checkType) }

        if (sidePlots.isEmpty()) {
            return emptyList()
        }

        return sidePlots
            .drop(1)
            .fold(
                mutableListOf(
                    Side(mutableListOf(sidePlots.first()))
                )
            ) { acc, curr ->
                val prev = when (orientation) {
                    HORIZONTAL -> curr.left()
                    VERTICAL -> curr.up()
                }

                if (prev == acc.last().last()) {
                    acc.last() += curr
                } else {
                    acc += Side(mutableListOf(curr))
                }

                acc
            }
    }

    private fun isSide(
        pos: Vec2,
        regionPositions: Set<Vec2>,
        checkType: CheckType
    ): Boolean = when (checkType) {
        FIRST -> {
            when (orientation) {
                HORIZONTAL -> pos.up() !in regionPositions
                VERTICAL -> pos.left() !in regionPositions
            }
        }

        SECOND -> {
            when (orientation) {
                HORIZONTAL -> pos.down() !in regionPositions
                VERTICAL -> pos.right() !in regionPositions
            }
        }
    }

}