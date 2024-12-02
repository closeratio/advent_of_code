package com.closeratio.aoc2024.day2

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day2Runner(
    private val resourceLoader: ResourceLoader,
    private val reportProcessor: ReportProcessor
) : Aoc2024Runner() {

    override fun getDay(): Int = 2

    override fun part1Function(): () -> Long = {
        reportProcessor.countSafeReports(
            resourceLoader.loadResourceLines("/2024_day_2_input.txt"),
        )
    }
}
