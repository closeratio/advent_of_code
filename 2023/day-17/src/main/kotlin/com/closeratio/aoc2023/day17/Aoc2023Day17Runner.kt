package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day17Runner(
    private val resourceLoader: ResourceLoader,
    private val heatLossRoutePlanner: HeatLossRoutePlanner
) : Aoc2023Runner() {

    override fun getDay(): Int = 17

    override fun part1Function(): () -> Long = {
        heatLossRoutePlanner.calculateMinimalHeatLoss(
            resourceLoader.loadResourceLines("/2023_day_17_input.txt")
        )
    }

    override fun part2Function() = null


}
