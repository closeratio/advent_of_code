package com.closeratio.aoc2023.day14

import com.closeratio.aoc.common.math.Vec2
import kotlin.math.max

class RockSimulation(
    val roundRocks: Set<Vec2>,
    val cubeRocks: Set<Vec2>
) {

    val maxY = max(
        roundRocks.maxOf(Vec2::y),
        cubeRocks.maxOf(Vec2::y)
    )

    fun calculateLoad(): Long {
        var currRoundRocks = roundRocks
        (0..maxY).forEach { y ->
            val currCandidates = currRoundRocks.filter { it.y == y }
            val newPositions = currCandidates.map { rock ->
                val nextRock = (currRoundRocks + cubeRocks)
                    .filter { it.y < rock.y && it.x == rock.x }
                    .maxByOrNull { it.y }

                if (nextRock != null) {
                    Vec2(rock.x, nextRock.y + 1)
                } else {
                    Vec2(rock.x, 0)
                }
            }

            currRoundRocks = (currRoundRocks - currCandidates) + newPositions
        }

        return currRoundRocks
            .map { (maxY - it.y) + 1 }
            .sum()
    }

}

