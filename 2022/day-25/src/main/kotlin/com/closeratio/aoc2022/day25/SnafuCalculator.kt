package com.closeratio.aoc2022.day25

class SnafuCalculator(
    private val lines: List<String>
) {

    fun calculateSnafuNumber(): String = lines
        .map(String::snafuToLong)
        .sum()
        .let(Long::toSnafu)

}