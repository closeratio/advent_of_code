package com.closeratio.aoc2024.day2

import kotlin.math.absoluteValue

data class Report(
    val values: List<Long>
) {
    fun isSafe(useDampener: Boolean): Boolean {
        if (!useDampener) {
            return isSafe()
        }

        if (isSafe()) {
            return true
        }

        return values
            .asSequence()
            .mapIndexed { index, _ -> removeAt(index) }
            .any { it.isSafe() }
    }

    private fun isSafe(): Boolean {
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

    private fun removeAt(index: Int): Report {
        val modifiedValues = values.toMutableList()
        modifiedValues.removeAt(index)
        return Report(modifiedValues)
    }
}
