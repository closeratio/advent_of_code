package com.closeratio.aoc2024.day6

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day6Runner(
    private val resourceLoader: ResourceLoader,
    private val guardSimulation: GuardSimulation
) : Aoc2024Runner() {

    override fun getDay(): Int = 6

    override fun part1Function(): () -> Long = {
        guardSimulation.countDistinctPositions(
            resourceLoader.loadResourceLines("/2024_day_6_input.txt"),
        ).toLong()
    }

}
