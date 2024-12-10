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
    ): Long = parseMap(input).sumTrailheadScores()

    fun sumTrailheadRatings(
        input: List<String>
    ): Long = parseMap(input).sumTrailheadRatings()

}

