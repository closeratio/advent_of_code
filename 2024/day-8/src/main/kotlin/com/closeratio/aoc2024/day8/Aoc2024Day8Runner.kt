package com.closeratio.aoc2024.day8

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day8Runner(
    private val resourceLoader: ResourceLoader,
    private val antennaAnalyser: AntennaAnalyser
) : Aoc2024Runner() {

    override fun getDay(): Int = 8

    override fun part1Function(): () -> Long = {
        antennaAnalyser.countAntinodes(
            resourceLoader.loadResourceLines("/2024_day_8_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        antennaAnalyser.countAntinodes(
            resourceLoader.loadResourceLines("/2024_day_8_input.txt"),
            true,
        )
    }

}
