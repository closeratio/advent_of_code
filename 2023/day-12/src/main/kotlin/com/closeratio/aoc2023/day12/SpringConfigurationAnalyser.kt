package com.closeratio.aoc2023.day12

import org.springframework.stereotype.Component
import kotlin.math.pow

@Component
class SpringConfigurationAnalyser {

    private fun generateArrangements(
        arrangementString: String
    ): List<String> {
        val unknownCount = arrangementString.count { it == '?' }
        val arrangements = (0..<2.0.pow(unknownCount).toInt()).map { arrangementIndex ->
            val binaryString = arrangementIndex
                .toString(2)
                .padStart(unknownCount, '0')
            var replacementString = arrangementString
            binaryString.forEach {
                val replacement = when (it) {
                    '1' -> '#'
                    '0' -> '.'
                    else -> throw IllegalArgumentException("Unexpected value: $it")
                }
                replacementString = replacementString.replaceFirst('?', replacement)
            }

            replacementString
        }

        return arrangements
    }

    private fun possibleArrangements(
        line: String
    ): Long {
        val (arrangementString, matchString) = line.split(" ")

        val regex = matchString.split(",")
            .joinToString(
                "\\.+",
                "^\\.*",
                "\\.*$"
            ) {
                "#{$it}"
            }
            .toRegex()

        return generateArrangements(arrangementString)
            .filter { regex.matches(it) }
            .size
            .toLong()
    }

    fun sumPossibleArrangements(
        lines: List<String>
    ): Long = lines.sumOf(::possibleArrangements)

}
