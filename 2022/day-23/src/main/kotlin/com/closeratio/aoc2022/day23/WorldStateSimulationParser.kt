package com.closeratio.aoc2022.day23

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.rules.EastRule
import com.closeratio.aoc2022.day23.rules.NorthRule
import com.closeratio.aoc2022.day23.rules.SouthRule
import com.closeratio.aoc2022.day23.rules.WestRule
import org.springframework.stereotype.Component

@Component
class WorldStateSimulationParser(
    private val resourceLoader: ResourceLoader
) {

    fun parse(path: String): WorldStateSimulation = resourceLoader
        .loadResourceLines(path)
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    '#' -> Vec2(x.toLong(), y.toLong())
                    else -> null
                }
            }
        }
        .map(::Elf)
        .let {
            WorldStateSimulation(
                WorldState(
                    it,
                    listOf(
                        NorthRule(),
                        SouthRule(),
                        WestRule(),
                        EastRule()
                    )
                )
            )
        }

}