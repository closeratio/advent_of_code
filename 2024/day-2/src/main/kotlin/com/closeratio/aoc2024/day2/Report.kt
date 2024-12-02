package com.closeratio.aoc2024.day2

import kotlin.math.absoluteValue

data class Report(
    val values: List<Long>
) {
    fun isSafe(): Boolean {
        val diffs = values
            .windowed(2)
            .map { (first, second) -> (first - second) }

        val withinLimits = diffs.all { it.absoluteValue in 1..3 }
        if (!withinLimits) {
            return false
        }

        val ascending = diffs.all { it < 0 }
        val descending = diffs.all { it > 0 }

        return ascending || descending
    }
}
