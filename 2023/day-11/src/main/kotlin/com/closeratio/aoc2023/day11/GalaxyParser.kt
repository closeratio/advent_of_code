package com.closeratio.aoc2023.day11

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class GalaxyParser {

    fun parse(
        lines: List<String>
    ): GalaxyAnalyser = lines
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    '#' -> Vec2(x, y)
                    else -> null
                }
            }
        }
        .toSet()
        .let(::GalaxyAnalyser)

}
