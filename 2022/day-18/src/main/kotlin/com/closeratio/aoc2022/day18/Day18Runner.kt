package com.closeratio.aoc2022.day18

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day18Runner(
    private val parser: LavaParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 18

    override fun part1Function(): () -> Int = {
        parser
            .parseLava("/2022_day_18_input.txt")
            .computeSurfaceArea()
    }

    override fun part2Function() = null

}
