package com.closeratio.aoc2024.day10

import com.closeratio.aoc.common.math.Vec2

data class TrailMap(
    val map: Map<Vec2, Int>
) {

    fun findHeads(): Set<Vec2> = map
        .filter { (_, v) -> v == 0 }
        .map(Map.Entry<Vec2, Int>::key)
        .toSet()

    fun sumTrailheadScores(): Long = findHeads().sumOf(::calculateTrailheadValue)

    fun sumTrailheadRatings(): Long = findHeads().sumOf { calculateTrailheadValue(it, true) }

    private fun calculateTrailheadValue(
        start: Vec2,
        countDistinctRoutes: Boolean = false
    ): Long {
        val ends = findEnds(start)

        return if (countDistinctRoutes) ends.size.toLong() else ends.toSet().size.toLong()
    }

    private fun findEnds(curr: Vec2): List<Vec2> {
        val currHeight = get(curr)
        if (currHeight == 9) {
            return listOf(curr)
        }

        return curr
            .immediatelyAdjacent()
            .filter { get(it) == currHeight + 1 }
            .flatMap(::findEnds)
    }

    operator fun get(pos: Vec2): Int = map.getOrDefault(pos, -1)

}