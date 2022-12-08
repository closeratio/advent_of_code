package com.closeratio.aoc2022.day8

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day8Runner(
    private val forestParser: ForestParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 8

    override fun part1Function(): () -> Int = {
        forestParser.parseForest("/2022_day_8_input.txt").computeVisibleTreeCount()
    }

    override fun part2Function(): () -> Int = {
        forestParser.parseForest("/2022_day_8_input.txt").computeOptimalTreehouseScenicScore()
    }

}
