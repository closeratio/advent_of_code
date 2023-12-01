package com.closeratio.aoc2023.day1

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day1Runner(
    private val resourceLoader: ResourceLoader,
    private val calibrationParser: CalibrationParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 1

    override fun part1Function(): () -> Long = {
        calibrationParser.sumCalibrationValues(
            resourceLoader.loadResourceLines("/2023_day_1_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        calibrationParser.sumCalibrationValues(
            resourceLoader.loadResourceLines("/2023_day_1_input.txt"),
            true
        )
    }

}
