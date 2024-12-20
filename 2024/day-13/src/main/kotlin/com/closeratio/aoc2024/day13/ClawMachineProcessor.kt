package com.closeratio.aoc2024.day13

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import org.springframework.stereotype.Service
import java.lang.System.lineSeparator

@Service
class ClawMachineProcessor {

    fun solve(
        input: String,
        prizeOffset: Vec2 = ZERO
    ): Long = input
        .split(lineSeparator() + lineSeparator())
        .map { ClawMachine.from(it, prizeOffset) }
        .sumOf(ClawMachine::solve)

}