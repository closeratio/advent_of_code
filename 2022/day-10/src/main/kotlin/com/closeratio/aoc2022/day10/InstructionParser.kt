package com.closeratio.aoc2022.day10

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class InstructionParser(
    private val resourceLoader: ResourceLoader
) {

    private fun parseLine(line: String): Instruction {
        val elements = line.split(" ")
        return when (elements.first()) {
            "noop" -> NoopInstruction()
            "addx" -> AddXInstruction(elements[1].toLong())
            else -> throw IllegalArgumentException("Unknown command: ${elements.first()}")
        }
    }

    fun parseInstructions(path: String): Computer = resourceLoader
        .loadResourceLines(path)
        .map(::parseLine)
        .let(::Computer)

}
