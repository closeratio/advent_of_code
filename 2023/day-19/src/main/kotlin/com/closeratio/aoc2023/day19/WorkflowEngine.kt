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

        val paths = computeAcceptPaths(workflowMap)
        var outstandingRangeFilter = Filter(
            1L..4000L,
            1L..4000L,
            1L..4000L,
            1L..4000L
        )
        val filters = paths
            .map {
                it.rules.fold(
                    outstandingRangeFilter
                ) { acc, curr ->
                    curr.updateFilter(acc, { outstandingRangeFilter }) {
                        outstandingRangeFilter = it
                    }
                }
            }
        return filters
            .map(Filter::totalCombinations)
            .sum()
    }

    private fun computeAcceptPaths(
        workflowMap: Map<String, Workflow>
    ): List<Path> = workflowMap.getValue("in")
        .rules
        .map { Path(listOf(it)) }
        .flatMap { findAcceptPaths(it, workflowMap) }

    private fun findAcceptPaths(
        currPath: Path,
        workflowMap: Map<String, Workflow>
    ): List<Path> {
        val last = currPath.rules.last()
        return when (last) {
            is TerminalRule -> {
                if (last.result) listOf(currPath) else emptyList()
            }

            is WorkflowRule -> workflowMap.getValue(last.id)
                .rules
                .map { currPath + it }
                .flatMap { findAcceptPaths(it, workflowMap) }

            is ComparisonRule -> {
                val outcome = last.outcome
                if (outcome is TerminalOutcome) {
                    if (outcome.accept) listOf(currPath) else emptyList()
                } else {
                    val workflowOutcome = outcome as WorkflowOutcome
                    workflowMap.getValue(workflowOutcome.workflowId)
                        .rules
                        .map { currPath + it }
                        .flatMap { findAcceptPaths(it, workflowMap) }
                }
            }

            else -> error("Unhandled rule type: ${last.javaClass.simpleName}")
        }
    }

}
