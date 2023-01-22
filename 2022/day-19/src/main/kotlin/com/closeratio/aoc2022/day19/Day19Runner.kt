package com.closeratio.aoc2022.day19

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day19Runner(
    private val parser: RobotSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 19

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day_19_input.txt")
            .computeQualityLevelSum(24L)
    }

    override fun part2Function() = null

}
