package com.closeratio.aoc2023.day18

import org.springframework.stereotype.Component
import kotlin.math.absoluteValue

@Component
class DigAnalyser(
    private val digSiteParser: DigSiteParser
) {

    fun computeDigArea(
        lines: List<String>,
        useHexInstructions: Boolean = false
    ): Long {
        val digSite = digSiteParser.parseDigSite(lines, useHexInstructions)
        val perimeter = (digSite.trenchCorners + digSite.trenchCorners.first())

        val interimInteriorArea = perimeter
            .windowed(2)
            .map { (first, second) ->
                (first.x * second.y) to (first.y * second.x)
            }
            .reduce { acc, pair -> (acc.first + pair.first) to (acc.second + pair.second) }
            .let { (first, second) ->
                (first - second) / 2
            }
            .absoluteValue + 1

        val trenchArea = perimeter
            .windowed(2) { (first, second) -> first.manhattanDistance(second) }
            .sum()

        return interimInteriorArea + (trenchArea / 2)
    }
}
