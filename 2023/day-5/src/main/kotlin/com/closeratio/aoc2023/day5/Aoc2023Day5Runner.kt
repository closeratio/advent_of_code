package com.closeratio.aoc2023.day5

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day5Runner(
    private val resourceLoader: ResourceLoader,
    private val almanacAnalyser: AlmanacAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 5

    override fun part1Function(): () -> Long = {
        almanacAnalyser.computeLowestLocationNumber(
            resourceLoader.loadResourceText("/2023_day_5_input.txt")
        )
    }

    override fun part2Function() = null

}
