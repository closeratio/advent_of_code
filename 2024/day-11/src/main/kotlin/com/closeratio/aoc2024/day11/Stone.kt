package com.closeratio.aoc2024.day11

import com.closeratio.aoc2024.day11.StoneCache.CacheKey

data class Stone(
    val value: Long
) {
    private val stringLength = value.toString().length

    fun blink(
        remainingBlinks: Int,
        stoneCache: StoneCache
    ): Long {
        if (remainingBlinks == 0) {
            return 1
        }

        val cacheKey = CacheKey(this, remainingBlinks)
        val result = stoneCache[cacheKey] ?: when {
            value == 0L -> listOf(1L)
            stringLength.mod(2) == 0 -> listOf(
                value.toString().dropLast(stringLength / 2).toLong(),
                value.toString().drop(stringLength / 2).toLong()
            )

            else -> listOf(value * 2024)
        }.map(::Stone)
            .sumOf { it.blink(remainingBlinks - 1, stoneCache) }

        if (cacheKey !in stoneCache) {
            stoneCache[cacheKey] = result
        }

        return result
    }

}