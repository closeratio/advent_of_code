package com.closeratio.aoc2023.day5

data class Mapping(
    val ranges: List<Range>
) {
    fun mapInputValue(value: Long): Long {
        val applicableRange = ranges.firstOrNull { value in it.inputRange } ?: return value

        return applicableRange.mapInputValue(value)
    }
}

