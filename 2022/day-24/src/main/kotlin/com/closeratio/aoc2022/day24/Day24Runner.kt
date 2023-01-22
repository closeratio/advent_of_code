package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day24Runner(
    private val parser: BlizzardSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 24

    override fun part1Function(): () -> Int = {
        parser
            .parse("/2022_day_24_input.txt")
            .calculateMinutesToGoal()
            .minute
    }

    override fun part2Function(): () -> Int = {
        parser
            .parse("/2022_day_24_input.txt")
            .calculateMinutesToGoalTwice()
            .minute
    }

}
