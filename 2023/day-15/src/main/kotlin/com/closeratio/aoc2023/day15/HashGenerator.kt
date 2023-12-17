package com.closeratio.aoc2023.day15

import org.springframework.stereotype.Component

@Component
class HashGenerator {

    private fun computeHash(
        data: String
    ): Long = data
        .map { it.code.toLong() }
        .fold(0) { acc, curr ->
            ((acc + curr) * 17).rem(256)
        }

    fun generateHash(
        data: String
    ): Long = data
        .split(",")
        .sumOf(::computeHash)

}