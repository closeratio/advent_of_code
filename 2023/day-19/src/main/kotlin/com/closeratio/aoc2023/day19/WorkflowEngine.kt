package com.closeratio.aoc2023.day19

import org.springframework.stereotype.Component

@Component
class WorkflowEngine(
    private val workflowParser: WorkflowParser,
    private val partParser: PartParser
) {

    fun sumAcceptedParts(input: String): Long = TODO()

}
