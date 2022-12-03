package com.closeratio.aoc.app

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day2Runner(
    private val gameParser: GameParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 2

    override fun part1Function(): () -> Long = {
        gameParser.computePlayPairPoints("/2022_day_2_input.txt")
    }

    override fun part2Function(): () -> Long = {
        gameParser.computePlayResultPairPoints("/2022_day_2_input.txt")
    }

}
