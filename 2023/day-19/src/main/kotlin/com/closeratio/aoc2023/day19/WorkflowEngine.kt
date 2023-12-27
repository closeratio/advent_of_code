package com.closeratio.aoc2023.day19

import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.TerminalOutcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome
import org.springframework.stereotype.Component

@Component
class WorkflowEngine(
    private val workflowParser: WorkflowParser,
    private val partParser: PartParser
) {

    fun sumAcceptedParts(input: String): Long {
        val workflow = workflowParser.parse(input).associateBy(Workflow::id)
        val parts = partParser.parse(input)

        return parts.mapNotNull { evaluatePart(it, workflow) }.sum()
    }

    private fun evaluatePart(part: Part, workflows: Map<String, Workflow>): Long? {
        var currentOutcome: Outcome = WorkflowOutcome("in")
        while (currentOutcome !is TerminalOutcome) {
            val id = (currentOutcome as WorkflowOutcome).workflowId
            currentOutcome = workflows.getValue(id).evaluate(part)
        }

        return if (currentOutcome.accept) part.sum else null
    }

}
