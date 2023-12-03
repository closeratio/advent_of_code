package com.closeratio.aoc2023.day3

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day3Runner(
    private val resourceLoader: ResourceLoader,
    private val schematicAnalyser: SchematicAnalyser
) : Aoc2023Runner() {

    override fun getDay(): Int = 3

    override fun part1Function(): () -> Long = {
        schematicAnalyser.partNumberSum(resourceLoader.loadResourceLines("/2023_day_3_input.txt"))
    }

    override fun part2Function() = null

}
