package com.closeratio.aoc2023.day14

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class RockSimulationParser {

    fun parse(
        lines: List<String>
    ): RockSimulation {
        val roundRocks = mutableSetOf<Vec2>()
        val cubeRocks = mutableSetOf<Vec2>()

        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                val pos = Vec2(x, y)
                when (char) {
                    '#' -> cubeRocks += pos
                    'O' -> roundRocks += pos
                }
            }
        }

        return RockSimulation(
            roundRocks,
            cubeRocks
        )
    }

}
