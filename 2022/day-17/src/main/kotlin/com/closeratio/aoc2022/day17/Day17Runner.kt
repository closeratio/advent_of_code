package com.closeratio.aoc2022.day17

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day17Runner(
    private val parser: GameStateSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 17

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day17_input.txt")
            .simulateUntilTotalRocksFallen(2022)
    }

    override fun part2Function(): () -> Long = {
        parser
            .parse("/2022_day17_input.txt")
            .simulateUntilTotalRocksFallen(1_000_000_000_000L)
    }

}
