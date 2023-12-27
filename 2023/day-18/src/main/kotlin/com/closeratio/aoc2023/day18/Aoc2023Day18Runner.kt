package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day18Runner(
    private val resourceLoader: ResourceLoader,
    private val digAnalyser: DigAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 18

    override fun part1Function(): () -> Long = {
        digAnalyser.computeDigArea(
            resourceLoader.loadResourceLines("/2023_day_18_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        digAnalyser.computeDigArea(
            resourceLoader.loadResourceLines("/2023_day_18_input.txt"),
            true
        )
    }


}
