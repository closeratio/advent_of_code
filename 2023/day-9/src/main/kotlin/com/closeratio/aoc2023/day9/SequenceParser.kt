package com.closeratio.aoc2023.day9

import org.springframework.stereotype.Component

@Component
class SequenceParser {

    fun parse(
        lines: List<String>
    ): SequenceAnalyser = lines
        .map(::parse)
        .let(::SequenceAnalyser)

    private fun parse(
        line: String
    ): NumberSequence = line
        .split(" ")
        .map(String::toLong)
        .let(::NumberSequence)

}