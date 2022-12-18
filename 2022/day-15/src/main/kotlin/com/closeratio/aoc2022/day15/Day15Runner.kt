package com.closeratio.aoc2022.day15

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day15Runner(
    private val sensorStateParser: SensorStateParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 15

    override fun part1Function(): () -> Int = {
        sensorStateParser
            .parseSensorState("/2022_day_15_input.txt")
            .invalidBeaconPositions(2_000_000)
    }

    override fun part2Function(): () -> Long = {
        sensorStateParser
            .parseSensorState("/2022_day_15_input.txt")
            .computeTuningFrequency(4_000_000)
    }

}
