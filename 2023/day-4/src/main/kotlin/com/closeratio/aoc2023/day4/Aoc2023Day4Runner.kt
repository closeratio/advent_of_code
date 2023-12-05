package com.closeratio.aoc2023.day4

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day4Runner(
    private val resourceLoader: ResourceLoader,
    private val scratchcardAnalyser: ScratchcardAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 4

    override fun part1Function(): () -> Long = {
        scratchcardAnalyser.sumScratchcardValues(
            resourceLoader.loadResourceLines("/2023_day_4_input.txt")
        )
    }

    override fun part2Function(): () -> Long = {
        scratchcardAnalyser.sumTotalScratchcards(
            resourceLoader.loadResourceLines("/2023_day_4_input.txt")
        )
    }

}
