package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day22Runner(
    private val parser: MonkeyMapParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 22

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day_22_input.txt")
            .computePassword()
    }

    override fun part2Function(): () -> Long = {
        parser
            .parse("/2022_day_22_input.txt", false)
            .computePassword()
    }

}
