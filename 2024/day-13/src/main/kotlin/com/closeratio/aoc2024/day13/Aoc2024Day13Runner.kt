package com.closeratio.aoc2024.day13

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day13Runner(
    private val resourceLoader: ResourceLoader,
    private val clawMachineProcessor: ClawMachineProcessor
) : Aoc2024Runner() {

    override fun getDay(): Int = 13

    override fun part1Function(): () -> Long = {
        clawMachineProcessor.solve(
            resourceLoader.loadResourceText("/2024_day_13_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        clawMachineProcessor.solve(
            resourceLoader.loadResourceText("/2024_day_13_input.txt"),
            Vec2(10000000000000, 10000000000000)
        )
    }

}
