package com.closeratio.aoc2024.day5

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day5Runner(
    private val resourceLoader: ResourceLoader,
    private val printQueueValidator: PrintQueueValidator
) : Aoc2024Runner() {

    override fun getDay(): Int = 5

    override fun part1Function(): () -> Long = {
        printQueueValidator.sumMiddlePageNumbers(
            resourceLoader.loadResourceText("/2024_day_5_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        printQueueValidator.sumMiddlePageNumbers(
            resourceLoader.loadResourceText("/2024_day_5_input.txt"),
            false,
        )
    }
}
