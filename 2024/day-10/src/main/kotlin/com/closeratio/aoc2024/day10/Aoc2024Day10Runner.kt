package com.closeratio.aoc2024.day10

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day10Runner(
    private val resourceLoader: ResourceLoader,
    private val trailheadAnalyser: TrailheadAnalyser
) : Aoc2024Runner() {

    override fun getDay(): Int = 10

    override fun part1Function(): () -> Long = {
        trailheadAnalyser.sumTrailheadScores(
            resourceLoader.loadResourceLines("/2024_day_10_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        trailheadAnalyser.sumTrailheadRatings(
            resourceLoader.loadResourceLines("/2024_day_10_input.txt"),
        )
    }

}
