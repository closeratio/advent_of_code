package com.closeratio.aoc2023.day19

import com.closeratio.aoc2023.day19.outcome.TerminalOutcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome
import com.closeratio.aoc2023.day19.rule.Comparison.GREATER_THAN
import com.closeratio.aoc2023.day19.rule.Comparison.LESS_THAN
import com.closeratio.aoc2023.day19.rule.ComparisonRule
import com.closeratio.aoc2023.day19.rule.Rule
import com.closeratio.aoc2023.day19.rule.TerminalRule
import com.closeratio.aoc2023.day19.rule.WorkflowRule
import org.springframework.stereotype.Component

@Component
class WorkflowParser {

    private fun parseComparisonRule(ruleString: String): Rule {
        val (partField) = ruleString.split("<", ">")
        val comparison = when {
            ruleString.contains(">") -> GREATER_THAN
            ruleString.contains("<") -> LESS_THAN
            else -> throw IllegalArgumentException("Unknown comparison for string '$ruleString'")
        }

        val valueString = ruleString.split("<", ">", ":")[1]
        val value = valueString.toLong()

        val (_, outcomeString) = ruleString.split(":")
        val outcome = when (outcomeString) {
            "A" -> TerminalOutcome(true)
            "R" -> TerminalOutcome(false)
            else -> WorkflowOutcome(outcomeString)
        }

        return ComparisonRule(
            partField,
            comparison,
            value,
            outcome
        )
    }

    private fun parseRule(ruleString: String): Rule {
        return when {
            ruleString == "A" -> TerminalRule(true)
            ruleString == "R" -> TerminalRule(false)
            !ruleString.contains("<") && !ruleString.contains(">") -> WorkflowRule(ruleString)
            else -> parseComparisonRule(ruleString)
        }
    }

    private fun parseWorkflow(line: String): Workflow {
        val (id, rulesString) = line.trim('}').split("{")
        val rules = rulesString.split(",").map(::parseRule)
        return Workflow(
            id,
            rules
        )
    }

    fun parse(input: String): List<Workflow> = input.split("\n\n")
        .first()
        .split("\n")
        .map(::parseWorkflow)

}

