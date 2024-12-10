package com.closeratio.aoc2024.day10

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Service

@Service
class TrailheadAnalyser {

    private fun parseMap(
        input: List<String>
    ): TrailMap = input
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Vec2(x, y) to c.toString().toInt()
            }
        }
        .toMap()
        .let(::TrailMap)

    fun sumTrailheadScores(
        input: List<String>
    ): Long {
        val map = parseMap(input)
        val trailheads = map.findHeads()

        return trailheads
            .map { calculateTrailheadScore(it, map) }
            .sum()
    }

    private fun calculateTrailheadScore(
        start: Vec2,
        trailMap: TrailMap
    ): Long {
        val ends = mutableSetOf<Vec2>()
        val toVisit = mutableSetOf(
            *start
            .immediatelyAdjacent()
            .filter { trailMap[it] == 1 }
            .toTypedArray()
        )

        while (toVisit.isNotEmpty()) {
            val curr = toVisit.first()
            toVisit.remove(curr)
            val currHeight = trailMap[curr]

            if (currHeight == 9) {
                ends += curr
            } else {
                toVisit += curr.immediatelyAdjacent().filter { trailMap[it] == currHeight + 1 }
            }
        }

        return ends.size.toLong()
    }

}

