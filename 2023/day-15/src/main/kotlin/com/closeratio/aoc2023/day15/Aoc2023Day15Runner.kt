package com.closeratio.aoc2023.day15

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day15Runner(
    private val resourceLoader: ResourceLoader,
    private val hashGenerator: HashGenerator,
    private val lensManager: LensManager
) : Aoc2023Runner() {

    override fun getDay(): Int = 15

    override fun part1Function(): () -> Long = {
        hashGenerator.generateHash(
            resourceLoader.loadResourceText("/2023_day_15_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        lensManager.computeFocusingPower(
            resourceLoader.loadResourceText("/2023_day_15_input.txt")
        )
    }


}
