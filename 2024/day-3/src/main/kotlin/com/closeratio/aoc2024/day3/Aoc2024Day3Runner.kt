package com.closeratio.aoc2024.day3

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day3Runner(
    private val resourceLoader: ResourceLoader,
    private val memoryScanner: MemoryScanner
) : Aoc2024Runner() {

    override fun getDay(): Int = 3

    override fun part1Function(): () -> Long = {
        memoryScanner.sumMulInstructions(
            resourceLoader.loadResourceText("/2024_day_3_input.txt"),
        )
    }
}
