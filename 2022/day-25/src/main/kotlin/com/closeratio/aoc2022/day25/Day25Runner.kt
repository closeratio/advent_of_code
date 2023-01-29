package com.closeratio.aoc2022.day25

import com.closeratio.aoc.common.Aoc2022Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Day25Runner(
    private val resourceLoader: ResourceLoader
) : Aoc2022Runner() {

    override fun getDay(): Int = 25

    override fun part1Function(): () -> String = {
        resourceLoader.loadResourceLines("/2022_day_25_input.txt")
            .let(::SnafuCalculator)
            .calculateSnafuNumber()
    }

    override fun part2Function() = null

}
