package com.closeratio.aoc2023.day19

import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.TerminalOutcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome
import com.closeratio.aoc2023.day19.rule.*
import org.springframework.stereotype.Component

@Component
class WorkflowEngine(
    private val workflowParser: WorkflowParser,
    private val partParser: PartParser
) {

    fun sumAcceptedParts(input: String): Long {
        val workflowMap = workflowParser.parse(input).associateBy(Workflow::id)
        val parts = partParser.parse(input)

        return parts.mapNotNull { evaluatePart(it, workflowMap) }.sum()
    }

    private fun evaluatePart(part: Part, workflows: Map<String, Workflow>): Long? {
        var currentOutcome: Outcome = WorkflowOutcome("in")
        while (currentOutcome !is TerminalOutcome) {
            val id = (currentOutcome as WorkflowOutcome).workflowId
            currentOutcome = workflows.getValue(id).evaluate(part)
        }

        return if (currentOutcome.accept) part.sum else null
    }

    fun sumTotalCombinations(input: String): Long {
        val workflowMap = workflowParser.parse(input).associateBy(Workflow::id)

        return workflowMap
            .getValue("in")
            .countCombinations(
                Ranges(1L..4000L, 1L..4000L, 1L..4000L, 1L..4000L),
                workflowMap
            )
    }

}
