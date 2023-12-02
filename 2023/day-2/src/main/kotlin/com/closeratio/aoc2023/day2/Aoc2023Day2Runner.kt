package com.closeratio.aoc2023.day2

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day2Runner(
    private val resourceLoader: ResourceLoader,
    private val gameAnalyser: GameAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 2

    override fun part1Function(): () -> Long = {
        gameAnalyser
            .computePossibleGames(resourceLoader.loadResourceLines("/2023_day_2_input.txt"))
    }

    override fun part2Function(): () -> Long = {
        gameAnalyser
            .sumPowerSets(resourceLoader.loadResourceLines("/2023_day_2_input.txt"))
    }

}
