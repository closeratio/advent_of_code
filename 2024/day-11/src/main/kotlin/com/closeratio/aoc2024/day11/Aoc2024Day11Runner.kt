package com.closeratio.aoc2024.day11

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day11Runner(
    private val resourceLoader: ResourceLoader,
    private val stoneProcessor: StoneProcessor
) : Aoc2024Runner() {

    override fun getDay(): Int = 11

    override fun part1Function(): () -> Long = {
        stoneProcessor.blink(
            resourceLoader.loadResourceText("/2024_day_11_input.txt"),
            25,
        )
    }

}
