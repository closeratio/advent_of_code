package com.closeratio.aoc2022.day1

data class CalorieGroup(
    val values: List<Long>
) {

    fun total() = values.sum()

}
