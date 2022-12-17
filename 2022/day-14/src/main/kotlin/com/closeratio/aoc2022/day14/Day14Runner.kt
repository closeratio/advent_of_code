package com.closeratio.aoc2022.day14

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day14Runner(
    private val sandSimulationParser: SandSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 14

    override fun part1Function(): () -> Int = {
        sandSimulationParser
            .parseSandSimulation("/2022_day_14_input.txt")
            .simulateUntilFinished()
    }

    override fun part2Function(): () -> Int = {
        sandSimulationParser
            .parseSandSimulation("/2022_day_14_input.txt")
            .simulateUntilFinished(true)
    }

}
