package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day16Runner(
    private val resourceLoader: ResourceLoader,
    private val beamAnalyser: BeamAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 16

    override fun part1Function(): () -> Long = {
        beamAnalyser.computeEnergisedCount(
            resourceLoader.loadResourceLines("/2023_day_16_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        beamAnalyser.computeOptimalDirection(
            resourceLoader.loadResourceLines("/2023_day_16_input.txt")
        )
    }


}
