package com.closeratio.aoc2023.day4

import kotlin.math.pow

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
            2.0.pow(matchCount - 1).toLong()
        }
    }

    fun calculateScratchcardCount(
        scratchcardMap: Map<Long, Scratchcard>,
        totalCache: MutableMap<Long, Long>
    ): Long {
        val matchCount = winningNumbers.intersect(heldNumber).size

        val result = if (matchCount == 0) {
            1
        } else {
            1 + ((id + 1)..(id + matchCount))
                .map(scratchcardMap::getValue)
                .sumOf {
                    totalCache[it.id] ?: it.calculateScratchcardCount(scratchcardMap, totalCache)
                }
        }
        totalCache[id] = result
        return result
    }

}