package com.closeratio.aoc2023.day19

import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.TerminalOutcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome
import com.closeratio.aoc2023.day19.rule.*
import com.closeratio.aoc2023.day19.rule.Comparison.GREATER_THAN
import com.closeratio.aoc2023.day19.rule.Comparison.LESS_THAN
import intersect

data class Workflow(
    val id: String,
    val rules: List<Rule>
) {

    fun evaluate(part: Part): Outcome = rules.firstNotNullOf { it.check(part) }

    fun countCombinations(
        ranges: Ranges,
        workflows: Map<String, Workflow>
    ): Long = rules.sumOf { rule ->
        when (rule) {
            is TerminalRule -> if (rule.accept) ranges.totalCombinations() else 0
            is WorkflowRule -> workflows.getValue(rule.id).countCombinations(ranges, workflows)
            is ComparisonRule -> countComparisonRuleCombinations(ranges, workflows, rule)
        }
    }

    private fun countComparisonRuleCombinations(
        ranges: Ranges,
        workflows: Map<String, Workflow>,
        rule: ComparisonRule
    ): Long {
        val narrowedRange = when (rule.partField) {
            "x" -> narrowRange(ranges.xRange, rule)
            "m" -> narrowRange(ranges.mRange, rule)
            "a" -> narrowRange(ranges.aRange, rule)
            "s" -> narrowRange(ranges.sRange, rule)
            else -> error("Unknown field name: ${rule.partField}")
        }

        if (narrowedRange.isEmpty()) {
            return 0L
        }

        return when (rule.outcome) {
            is TerminalOutcome -> if (rule.outcome.accept) ranges.totalCombinations() else 0L
            is WorkflowOutcome -> workflows
                .getValue(rule.outcome.workflowId)
                .countCombinations(
                    when (rule.partField) {
                        "x" -> ranges.copy(xRange = narrowedRange)
                        "m" -> ranges.copy(mRange = narrowedRange)
                        "a" -> ranges.copy(aRange = narrowedRange)
                        "s" -> ranges.copy(sRange = narrowedRange)
                        else -> error("Unknown field name: ${rule.partField}")
                    },
                    workflows
                )
        }
    }


    private fun narrowRange(
        currRange: LongRange,
        rule: ComparisonRule
    ): LongRange = when (rule.comparison) {
        GREATER_THAN -> ((rule.value + 1)..4000L).intersect(currRange)
        LESS_THAN -> (0L..<rule.value).intersect(currRange)
    }

}
