package com.closeratio.aoc2023.day6

data class Race(
    val buttonTime: Long
) {

    fun distance(time: Long): Long = java.lang.Long.max(0, time - buttonTime) * buttonTime

}
