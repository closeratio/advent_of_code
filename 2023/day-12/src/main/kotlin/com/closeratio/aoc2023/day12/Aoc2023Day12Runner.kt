package com.closeratio.aoc2023.day12

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day12Runner(
    private val resourceLoader: ResourceLoader,
    private val springConfigurationAnalyser: SpringConfigurationAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 12

    override fun part1Function(): () -> Long = {
        springConfigurationAnalyser.sumPossibleArrangements(
            resourceLoader.loadResourceLines("/2023_day_12_input.txt")
        )
    }

    override fun part2Function() = null


}
