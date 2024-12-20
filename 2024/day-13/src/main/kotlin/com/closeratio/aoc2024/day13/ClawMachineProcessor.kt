package com.closeratio.aoc2024.day13

import org.springframework.stereotype.Service
import java.lang.System.lineSeparator

@Service
class ClawMachineProcessor {

    fun solve(
        input: String
    ): Long = input
        .split(lineSeparator() + lineSeparator())
        .map(ClawMachine.Companion::from)
        .sumOf(ClawMachine::solve)

}