package com.closeratio.aoc2023.day19

import org.springframework.stereotype.Component

@Component
class PartParser {
    fun parse(input: String): List<Part> = input
        .split("\n\n")[1]
        .split("\n")
        .map { it.trim('{', '}') }
        .map { line ->
            val (x, m, a, s) = line.split(",")
                .map { it.split("=")[1] }
                .map(String::toLong)
            Part(x, m, a, s)
        }
}
