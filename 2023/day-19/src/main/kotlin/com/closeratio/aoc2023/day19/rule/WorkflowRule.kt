package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.WorkflowOutcome

data class WorkflowRule(
    val id: String
) : Rule() {

    override fun check(part: Part): Outcome = WorkflowOutcome(id)

    override fun updateFilter(
        filter: Filter,
        getOutstandingRangeFilter: () -> Filter,
        updateOutsideRangeFilter: (Filter) -> Unit
    ): Filter = filter
}

