package com.closeratio.aoc2024.day7

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day7Runner(
    private val resourceLoader: ResourceLoader,
    private val bridgeCalibrator: BridgeCalibrator
) : Aoc2024Runner() {

    override fun getDay(): Int = 7

    override fun part1Function(): () -> Long = {
        bridgeCalibrator.calibrate(
            resourceLoader.loadResourceLines("/2024_day_7_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        bridgeCalibrator.calibrate(
            resourceLoader.loadResourceLines("/2024_day_7_input.txt"),
            true,
        )
    }

}
