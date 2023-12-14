package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day13Runner(
    private val resourceLoader: ResourceLoader,
    private val mirrorAnalyser: MirrorAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 13

    override fun part1Function(): () -> Long = {
        mirrorAnalyser.summarise(
            resourceLoader.loadResourceText("/2023_day_13_input.txt")
        )
    }

    override fun part2Function() = null


}
