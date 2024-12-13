package com.closeratio.aoc2024.day9

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.common.Aoc2024Runner
import org.springframework.stereotype.Component

@Component
class Aoc2024Day9Runner(
    private val resourceLoader: ResourceLoader,
    private val diskFragmenter: DiskFragmenter
) : Aoc2024Runner() {

    override fun getDay(): Int = 9

    override fun part1Function(): () -> Long = {
        diskFragmenter.fragmentAndCalculateChecksum(
            resourceLoader.loadResourceText("/2024_day_9_input.txt"),
        )
    }

    override fun part2Function(): () -> Long = {
        diskFragmenter.defragmentAndCalculateChecksum(
            resourceLoader.loadResourceText("/2024_day_9_input.txt"),
        )
    }

}
