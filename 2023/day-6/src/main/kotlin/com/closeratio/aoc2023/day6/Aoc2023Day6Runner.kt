package com.closeratio.aoc2023.day6

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day6Runner(
    private val resourceLoader: ResourceLoader,
    private val raceAnalyser: RaceAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 6

    override fun part1Function(): () -> Long = {
        raceAnalyser.computePossibleRecords(
            resourceLoader.loadResourceLines("/2023_day_6_input.txt")
        )
    }

    override fun part2Function() = null

}
