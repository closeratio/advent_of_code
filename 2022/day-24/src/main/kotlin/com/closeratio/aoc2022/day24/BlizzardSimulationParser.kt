package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day24.Blizzard.Direction.*
import org.springframework.stereotype.Component

@Component
class BlizzardSimulationParser(
    private val resourceLoader: ResourceLoader
) {

    fun parse(path: String): BlizzardSimulation = resourceLoader
        .loadResourceLines(path)
        .mapIndexed { y, chars ->
            val blizzards = mutableSetOf<Blizzard>()
            val walls = mutableSetOf<Vec2>()

            chars.forEachIndexed { x, char ->
                val pos = Vec2(x, y)
                when (char) {
                    '#' -> walls += pos
                    '>' -> blizzards += Blizzard(pos, RIGHT)
                    '<' -> blizzards += Blizzard(pos, LEFT)
                    '^' -> blizzards += Blizzard(pos, UP)
                    'v' -> blizzards += Blizzard(pos, DOWN)
                }
            }

            blizzards to walls
        }
        .let {
            val blizzards = it.flatMap(Pair<MutableSet<Blizzard>, MutableSet<Vec2>>::first)
            val walls = it
                .flatMap(Pair<MutableSet<Blizzard>, MutableSet<Vec2>>::second)
                .toSet()
                .let(::Walls)

            BlizzardSimulation(
                walls,
                BlizzardState(
                    blizzards,
                    0
                )
            )
        }

}
