package com.closeratio.aoc2022.day4

import com.closeratio.aoc.common.Aoc2022Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Day4Runner(
    private val resourceLoader: ResourceLoader
) : Aoc2022Runner() {

    override fun getDay(): Int = 4

    override fun part1Function(): () -> Int = {
        val list = resourceLoader
            .loadResourceLines("/2022_day_4_input.txt")
            .let(AssignmentList.Companion::from)

        list.computeFullyOverlappingPairs()
    }

    override fun part2Function(): () -> Int = {
        val list = resourceLoader
            .loadResourceLines("/2022_day_4_input.txt")
            .let(AssignmentList.Companion::from)

        list.computePartlyOverlappingPairs()
    }

}
