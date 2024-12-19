package com.closeratio.aoc2024.day11

data class StoneSet(
    val stones: List<Stone>
) {
    val count: Long = stones.size.toLong()

    fun blink(
        blinkCount: Int
    ): Long {
        val cache = StoneCache()
        return stones.sumOf { it.blink(blinkCount, cache) }
    }

    companion object {
        fun from(input: String): StoneSet = input
            .split(" ")
            .map(String::toLong)
            .map(::Stone)
            .let(::StoneSet)
    }
}