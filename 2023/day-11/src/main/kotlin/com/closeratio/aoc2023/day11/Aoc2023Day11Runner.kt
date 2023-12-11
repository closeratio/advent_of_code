package com.closeratio.aoc2023.day11

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day11Runner(
    private val resourceLoader: ResourceLoader,
    private val galaxyParser: GalaxyParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 11

    override fun part1Function(): () -> Long = {
        galaxyParser.parse(
            resourceLoader.loadResourceLines("/2023_day_11_input.txt")
        ).sumShortestPaths()
    }

    override fun part2Function(): () -> Long = {
        galaxyParser.parse(
            resourceLoader.loadResourceLines("/2023_day_11_input.txt")
        ).sumShortestPaths(1000000)
    }


}
