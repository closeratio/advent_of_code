package com.closeratio.aoc2022.day21

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day21Runner(
    private val parser: MonkeyTreeParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 21

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day_21_input.txt")
            .computeRootNumber()
    }

    override fun part2Function(): () -> Long = {
        parser
            .parse("/2022_day_21_input.txt")
            .computeRequiredHumanNumber()
    }

}
