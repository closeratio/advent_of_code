package com.closeratio.aoc2024.day4

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day4Runner(
    private val resourceLoader: ResourceLoader,
    private val xmasCounter: XmasCounter
) : Aoc2024Runner() {

    override fun getDay(): Int = 3

    override fun part1Function(): () -> Long = {
        xmasCounter.countXmas(
            resourceLoader.loadResourceLines("/2024_day_4_input.txt"),
        ).toLong()
    }
}
