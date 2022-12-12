package com.closeratio.aoc2022.day11

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day11Runner(
    private val monkeyParser: MonkeyParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 11

    override fun part1Function(): () -> Long = {
        monkeyParser
            .parseInput("/2022_day_11_input.txt")
            .calculateMonkeyBusiness(20)
    }

    override fun part2Function(): () -> Long = {
        monkeyParser
            .parseInput("/2022_day_11_input.txt", false)
            .calculateMonkeyBusiness(10_000)
    }

}
