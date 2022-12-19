package com.closeratio.aoc2022.day16

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day16Runner(
    private val parser: ValveStateSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 16

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day_16_input.txt")
            .computeMaxPossiblePressureSolo()
    }

    override fun part2Function(): () -> Long = {
        parser
            .parse("/2022_day_16_input.txt")
            .computeMaxPossiblePressureWithElephantFriend()
    }

}
