package com.closeratio.aoc2022.day23

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day23Runner(
    private val parser: WorldStateSimulationParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 23

    override fun part1Function(): () -> Long = {
        parser
            .parse("/2022_day23_input.txt")
            .computeEmptyGroundTiles(10)
    }

    override fun part2Function(): () -> Long = {
        parser
            .parse("/2022_day23_input.txt")
            .computeRoundsUntilFinished()
    }

}
