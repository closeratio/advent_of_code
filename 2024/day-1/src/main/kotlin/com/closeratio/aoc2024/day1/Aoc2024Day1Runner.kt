package com.closeratio.aoc2024.day1

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day1Runner(
    private val resourceLoader: ResourceLoader,
    private val locationListProcessor: LocationListProcessor
) : Aoc2024Runner() {

    override fun getDay(): Int = 1

    override fun part1Function(): () -> Long = {
        locationListProcessor.computeDistance(
            resourceLoader.loadResourceLines("/input_day1.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        locationListProcessor.computeSimilarityScore(
            resourceLoader.loadResourceLines("/input_day1.txt"),
        )
    }
}
