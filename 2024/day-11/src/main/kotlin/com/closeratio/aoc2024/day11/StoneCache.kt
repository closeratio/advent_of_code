package com.closeratio.aoc2024.day11

class StoneCache {

    data class CacheKey(
        val stone: Stone,
        val remainingBlinks: Int
    )

    private val cache: MutableMap<CacheKey, Long> = mutableMapOf()

    operator fun get(key: CacheKey): Long? = cache[key]
    operator fun set(key: CacheKey, value: Long) {
        cache[key] = value
    }

    operator fun contains(key: CacheKey): Boolean = cache.contains(key)

}