package com.closeratio.aoc2023.day5

data class Range(
    val inputRange: LongRange,
    val outputRange: LongRange
) {

    fun mapInputValue(value: Long): Long {
        val delta = value - inputRange.first
        return outputRange.first + delta
    }

    fun mapOutputValue(value: Long): Long {
        val delta = value - outputRange.first
        return inputRange.first + delta
    }

}
