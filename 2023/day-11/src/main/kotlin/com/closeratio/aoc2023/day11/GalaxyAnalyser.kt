package com.closeratio.aoc2023.day11

import com.closeratio.aoc.common.math.Vec2

class GalaxyAnalyser(
    val stars: Set<Vec2>
) {

    private fun growGalaxy(): Set<Vec2> {
        var currOffset = 0L
        val offsetXStars = (stars.minOf(Vec2::x)..stars.maxOf(Vec2::x))
            .flatMap { x ->
                val matchingStars = stars.filter { it.x == x }
                if (matchingStars.isNotEmpty()) {
                    matchingStars.map { Vec2(it.x + currOffset, it.y) }
                } else {
                    currOffset++
                    emptyList()
                }
            }

        currOffset = 0
        return (stars.minOf(Vec2::y)..stars.maxOf(Vec2::y))
            .flatMap { y ->
                val matchingStars = offsetXStars.filter { it.y == y }
                if (matchingStars.isNotEmpty()) {
                    matchingStars.map { Vec2(it.x, it.y + currOffset) }
                } else {
                    currOffset++
                    emptyList()
                }
            }
            .toSet()
    }

    fun sumShortestPaths(): Long {
        val grownGalaxy = growGalaxy()

        val checkedStars = mutableSetOf<Vec2>()
        return grownGalaxy
            .sumOf { startingStar ->
                checkedStars += startingStar
                grownGalaxy
                    .filter { it !in checkedStars }
                    .sumOf { endingStar ->
                        endingStar.manhattanDistance(startingStar)
                    }
            }
    }

}
