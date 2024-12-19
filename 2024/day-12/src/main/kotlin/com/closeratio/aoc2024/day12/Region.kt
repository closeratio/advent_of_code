package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day12.FenceCalculator.CalculationMethod
import com.closeratio.aoc2024.day12.FenceCalculator.CalculationMethod.INDIVIDUAL_PANELS
import com.closeratio.aoc2024.day12.FenceCalculator.CalculationMethod.REGION_SIDES
import com.closeratio.aoc2024.day12.Orientation.HORIZONTAL
import com.closeratio.aoc2024.day12.Orientation.VERTICAL

data class Region(
    val plots: Set<Plot>
) {
    private val regionType = plots.first().type
    private val posSet = plots.map(Plot::pos).toSet()

    private val minX = posSet.minOf(Vec2::x)
    private val minY = posSet.minOf(Vec2::y)
    private val maxX = posSet.maxOf(Vec2::x)
    private val maxY = posSet.maxOf(Vec2::y)

    fun calculateFencePrice(
        calculationMethod: CalculationMethod
    ): Long {
        return when (calculationMethod) {
            INDIVIDUAL_PANELS -> plots.sumOf { it.calculateRequiredFence(posSet) } * plots.size.toLong()
            REGION_SIDES -> {
                val sides = createPlotLines().flatMap { plotLine -> plotLine.calculateSides(posSet) }

                plots.size * sides.size.toLong()
            }
        }
    }

    private fun createPlotLines(): List<PlotLine> {
        val horizontal = (minY..maxY)
            .map { y ->
                PlotLine(
                    (minX..maxX)
                        .map { x -> Plot(Vec2(x, y), regionType) }
                        .filter { it in plots }
                        .toSet(),
                    HORIZONTAL
                )
            }

        val vertical = (minX..maxX).map { x ->
            PlotLine(
                (minY..maxY)
                    .map { y -> Plot(Vec2(x, y), regionType) }
                    .filter { it in plots }
                    .toSet(),
                VERTICAL
            )
        }

        return (horizontal + vertical)
            .filter { it.plots.isNotEmpty() }
    }

}
