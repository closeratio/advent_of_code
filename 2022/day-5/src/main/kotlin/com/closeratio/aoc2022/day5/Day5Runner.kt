package com.closeratio.aoc2022.day5

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day5Runner(
    private val instructionRunner: InstructionRunner
) : Aoc2022Runner() {

    override fun getDay(): Int = 5

    override fun part1Function(): () -> String = {
        val (state, instructions) = instructionRunner.loadState("/2022_day_5_input.txt")

        val endState = instructionRunner.runInstructions(state, instructions)
        endState.topLineString()
    }

    override fun part2Function(): () -> String = {
        val (state, instructions) = instructionRunner.loadState("/2022_day_5_input.txt")

        val endState = instructionRunner.runInstructions(state, instructions, true)
        endState.topLineString()
    }

}
