package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome

class WorkflowRule(
    private val id: String
) : Rule() {

    override fun check(part: Part): Outcome = WorkflowOutcome(id)
}

