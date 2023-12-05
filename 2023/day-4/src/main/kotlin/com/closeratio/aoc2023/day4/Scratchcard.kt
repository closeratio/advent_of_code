package com.closeratio.aoc2023.day4

data class Scratchcard(
    val id: Long,
    val winningNumbers: Set<Long>,
    val heldNumber: Set<Long>
) {

    fun calculateValue(): Long {
        val matchCount = winningNumbers.intersect(heldNumber).size

        return if (matchCount == 0) {
            0
        } else {
            Math.pow(2.0, (matchCount - 1).toDouble()).toLong()
        }
    }

}