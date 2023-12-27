package com.closeratio.aoc2023.day19

import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.rule.Rule

data class Workflow(
    val id: String,
    val rules: List<Rule>
) {

    fun evaluate(part: Part): Outcome = rules.firstNotNullOf { it.check(part) }

}
