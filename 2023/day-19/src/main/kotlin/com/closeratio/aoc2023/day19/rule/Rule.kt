package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome

abstract class Rule {
    abstract fun check(part: Part): Outcome?

    abstract fun updateFilter(
        filter: Filter,
        getOutstandingRangeFilter: () -> Filter,
        updateOutsideRangeFilter: (Filter) -> Unit
    ): Filter
}

