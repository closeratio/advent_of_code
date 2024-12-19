package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import com.closeratio.aoc2024.day12.FenceCalculator.CalculationMethod.REGION_SIDES
import org.springframework.stereotype.Component

@Component
class Aoc2024Day12Runner(
    private val resourceLoader: ResourceLoader,
    private val fenceCalculator: FenceCalculator
) : Aoc2024Runner() {

    override fun getDay(): Int = 12

    override fun part1Function(): () -> Long = {
        fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/2024_day_12_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/2024_day_12_input.txt"),
            REGION_SIDES,
        )
    }

}
