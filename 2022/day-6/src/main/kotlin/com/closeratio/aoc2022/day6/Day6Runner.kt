package com.closeratio.aoc2022.day6

import com.closeratio.aoc.common.Aoc2022Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Day6Runner(
    private val resourceLoader: ResourceLoader
) : Aoc2022Runner() {

    override fun getDay(): Int = 6

    override fun part1Function(): () -> Int = {
        Datastream(resourceLoader.loadResourceText("/2022_day_6_input.txt"))
            .computePacketIndex()
    }

    override fun part2Function(): () -> Int = {
        Datastream(resourceLoader.loadResourceText("/2022_day_6_input.txt"))
            .computeMessageIndex()
    }

}
