package com.closeratio.aoc2022.day2

import com.closeratio.aoc2022.common.AocRunner
import org.springframework.stereotype.Component

@Component
class Day2Runner(
    private val gameParser: GameParser
) : AocRunner() {

    override fun getDay(): Int = 2

    override fun part1Function(): () -> Long = {
        gameParser.computePlayPairPoints("/day_2_input.txt")
    }

    override fun part2Function(): () -> Long = {
        gameParser.computePlayResultPairPoints("/day_2_input.txt")
    }

}
