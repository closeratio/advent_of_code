package com.closeratio.aoc2022.day20

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day20Runner(
    private val parser: ListParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 20

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day_20_input.txt")
            .mixAndSum()
    }

    override fun part2Function() = null

}
