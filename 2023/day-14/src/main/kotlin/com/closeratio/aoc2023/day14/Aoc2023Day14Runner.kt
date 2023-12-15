package com.closeratio.aoc2023.day14

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day14Runner(
    private val resourceLoader: ResourceLoader,
    private val rockSimulationParser: RockSimulationParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 14

    override fun part1Function(): () -> Long = {
        rockSimulationParser.parse(
            resourceLoader.loadResourceLines("/2023_day_14_input.txt")
        ).calculateLoad()
    }

    override fun part2Function() = null


}
