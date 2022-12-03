package com.closeratio.aoc2022.day3

import com.closeratio.aoc2022.common.Aoc2022Runner
import com.closeratio.aoc2022.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Day3Runner(
    private val resourceLoader: ResourceLoader
) : Aoc2022Runner() {

    override fun getDay(): Int = 3

    override fun part1Function(): () -> Long = {
        val backpacks = resourceLoader
            .loadResourceLines("/2022_day_3_input.txt")
            .map(Backpack.Companion::from)

        backpacks.sumOf(Backpack::prioritySum)
    }

    override fun part2Function(): () -> Long = {
        resourceLoader
            .loadResourceLines("/2022_day_3_input.txt")
            .chunked(3)
            .map { (first, second, third) ->
                Backpack.from(first).commonItem(Backpack.from(second), Backpack.from(third))
            }
            .sumOf(Item::priority)
    }

}
