package com.closeratio.aoc2023.day9

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day9Runner(
    private val resourceLoader: ResourceLoader,
    private val sequenceParser: SequenceParser
) : Aoc2023Runner() {

    override fun getDay(): Int = 9

    override fun part1Function(): () -> Long = {
        sequenceParser.parse(
            resourceLoader.loadResourceLines("/2023_day_9_input.txt")
        ).sumExtrapolatedValues()
    }

    override fun part2Function() = null

}
