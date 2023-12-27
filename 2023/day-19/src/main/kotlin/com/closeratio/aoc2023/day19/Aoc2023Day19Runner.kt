package com.closeratio.aoc2023.day19

import com.closeratio.aoc.common.Aoc2023Runner
import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class Aoc2023Day19Runner(
    private val resourceLoader: ResourceLoader,
    private val workflowEngine: WorkflowEngine
) : Aoc2023Runner() {

    override fun getDay(): Int = 19

    override fun part1Function(): () -> Long = {
        workflowEngine.sumAcceptedParts(
            resourceLoader.loadResourceText("/2023_day_19_input.txt")
        )
    }

    override fun part2Function() = null


}
