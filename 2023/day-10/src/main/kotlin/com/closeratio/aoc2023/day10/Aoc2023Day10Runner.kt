package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day10Runner(
    private val resourceLoader: ResourceLoader,
    private val pipeParser: PipeParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 10

    override fun part1Function(): () -> Long = {
        pipeParser.parse(
            resourceLoader.loadResourceLines("/2023_day_10_input.txt")
        ).farthestDistanceFromStart().value
    }

    override fun part2Function(): () -> Long = {
        pipeParser.parse(
            resourceLoader.loadResourceLines("/2023_day_10_input.txt")
        ).calculateTilesEnclosedByLoop()
    }

}
