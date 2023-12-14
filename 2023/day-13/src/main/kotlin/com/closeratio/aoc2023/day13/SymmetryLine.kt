package com.closeratio.aoc2023.day13

data class SymmetryLine(
    val size: Long,
    val orientation: Orientation
) {

    val summaryValue = size * orientation.multiplier

    enum class Orientation(
        val multiplier: Long
    ) {
        HORIZONTAL(100),
        VERTICAL(1)
    }

}
