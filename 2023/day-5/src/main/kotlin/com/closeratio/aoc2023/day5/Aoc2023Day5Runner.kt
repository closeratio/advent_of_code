package com.closeratio.aoc2023.day5

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day5Runner(
    private val resourceLoader: ResourceLoader,
    private val almanacParser: AlmanacParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 5

    override fun part1Function(): () -> Long = {
        almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/2023_day_5_input.txt"), false)
            .computeLowestLocationNumber()
    }

    override fun part2Function(): () -> Long = {
        almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/2023_day_5_input.txt"), true)
            .computeLowestLocationNumber()
    }

}
