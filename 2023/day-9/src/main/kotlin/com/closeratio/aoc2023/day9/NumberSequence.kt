package com.closeratio.aoc2023.day9

data class NumberSequence(
    val values: List<Long>
) {
    fun getNextValue(): Long {
        return if (values.all { it == 0L }) {
            0
        } else {
            values.last() + derive().getNextValue()
        }
    }

    private fun derive(): NumberSequence = values
        .windowed(2, 1)
        .map { (first, second) -> second - first }
        .let(::NumberSequence)

    fun getPreviousValue(): Long {
        return if (values.all { it == 0L }) {
            0
        } else {
            values.first() - derive().getPreviousValue()
        }
    }
}
