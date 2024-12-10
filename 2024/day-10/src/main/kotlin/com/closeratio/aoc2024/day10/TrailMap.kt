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
        val ends = mutableListOf<Vec2>()
        val toVisit = mutableSetOf(
            *start
                .immediatelyAdjacent()
                .filter { get(it) == 1 }
                .toTypedArray()
        )

        while (toVisit.isNotEmpty()) {
            val curr = toVisit.first()
            toVisit.remove(curr)
            val currHeight = get(curr)

            if (currHeight == 9) {
                ends += curr
            } else {
                toVisit += curr.immediatelyAdjacent().filter { get(it) == currHeight + 1 }
            }
        }

        return if (countDistinctRoutes) ends.size.toLong() else ends.toSet().size.toLong()
    }

    operator fun get(pos: Vec2): Int = map.getOrDefault(pos, -1)

}