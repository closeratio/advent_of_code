package com.closeratio.aoc2022.day10

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day10Runner(
    private val instructionParser: InstructionParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 10

    override fun part1Function(): () -> Long = {
        instructionParser
            .parseInstructions("/2022_day_10_input.txt")
            .computeSignalStrengthSum()
    }

    override fun part2Function() = null

}
