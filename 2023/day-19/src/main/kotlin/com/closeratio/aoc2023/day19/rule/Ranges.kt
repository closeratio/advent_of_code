package com.closeratio.aoc2023.day19.rule

import size

data class Ranges(
    val xRange: LongRange,
    val mRange: LongRange,
    val aRange: LongRange,
    val sRange: LongRange
) {

    fun totalCombinations(): Long = xRange.size * mRange.size * aRange.size * sRange.size

}
