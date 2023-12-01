package com.closeratio.aoc2023.day1

import org.springframework.stereotype.Component

@Component
class CalibrationParser {

    private val digitMap = mapOf(
        "one" to 1L,
        "two" to 2L,
        "three" to 3L,
        "four" to 4L,
        "five" to 5L,
        "six" to 6L,
        "seven" to 7L,
        "eight" to 8L,
        "nine" to 9L
    )

    private fun getDigits(line: String, includeWords: Boolean): List<Long> {
        var remainingString = line
        val digits = mutableListOf<Long>()
        while (remainingString.isNotEmpty()) {
            if ("""\d""".toRegex().matches(remainingString.first().toString())) {
                digits.add(remainingString.first().toString().toLong())
            } else if (includeWords) {
                digitMap.forEach { name, value ->
                    if (remainingString.startsWith(name)) {
                        digits.add(value)
                    }
                }
            }

            remainingString = remainingString.drop(1)
        }

        return digits
    }

    fun sumCalibrationValues(
        lines: List<String>,
        includeWords: Boolean = false
    ): Long = lines
        .map { getDigits(it, includeWords) }
        .map { it.first() to it.last() }
        .map { (first, second) -> "$first$second" }
        .map(String::toLong)
        .sum()

}
