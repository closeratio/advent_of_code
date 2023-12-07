package com.closeratio.aoc2023.day7

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day7Runner(
    private val resourceLoader: ResourceLoader,
    private val handAnalyser: HandAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 7

    override fun part1Function(): () -> Long = {
        handAnalyser.computeTotalWinnings(
            resourceLoader.loadResourceLines("/2023_day_7_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        handAnalyser.computeTotalWinnings(
            resourceLoader.loadResourceLines("/2023_day_7_input.txt"),
            true
        )
    }

}
