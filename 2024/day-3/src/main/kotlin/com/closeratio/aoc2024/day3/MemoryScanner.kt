package com.closeratio.aoc2024.day3

import org.springframework.stereotype.Service

@Service
class MemoryScanner {

    private val mulRegex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()

    fun sumMulInstructions(input: String): Long = mulRegex
        .findAll(input)
        .map { result ->
            val matched = result.groupValues.first()
            matched.drop(4).dropLast(1).split(",")
        }
        .map { (lString, rString) -> lString.toLong() * rString.toLong() }
        .sum()

}