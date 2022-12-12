package com.closeratio.aoc2022.day12

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day12Runner(
    private val heightMapParser: HeightMapParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 12

    override fun part1Function(): () -> Int = {
        heightMapParser
            .parseHeightMap("/2022_day_12_input.txt")
            .computePathLength()
    }

    override fun part2Function(): () -> Int = {
        heightMapParser
            .parseHeightMap("/2022_day_12_input.txt")
            .shortestRouteStartingFrom("a")
    }

}
