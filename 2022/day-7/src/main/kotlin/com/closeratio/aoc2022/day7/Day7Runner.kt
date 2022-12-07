package com.closeratio.aoc2022.day7

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day7Runner(
    private val directoryAnalyser: DirectoryAnalyser
) : Aoc2022Runner() {

    override fun getDay(): Int = 7

    override fun part1Function(): () -> Long = {
        directoryAnalyser.sumDirectoriesWithMaxSize("/2022_day_7_input.txt", 100_000)
    }

    override fun part2Function(): () -> Long = {
        directoryAnalyser.computeDirectorySizeToDelete("/2022_day_7_input.txt")
    }

}
