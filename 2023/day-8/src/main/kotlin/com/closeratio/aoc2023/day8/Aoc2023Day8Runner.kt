package com.closeratio.aoc2023.day8

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day8Runner(
    private val resourceLoader: ResourceLoader,
    private val networkParser: NetworkParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 8

    override fun part1Function(): () -> Long = {
        networkParser.parse(
            resourceLoader.loadResourceLines("/2023_day_8_input.txt")
        ).stepCount()
    }

    override fun part2Function(): () -> Long = {
        networkParser.parse(
            resourceLoader.loadResourceLines("/2023_day_8_input.txt")
        ).stepCountSimultaneous()
    }

}
