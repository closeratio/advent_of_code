package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome

sealed class Rule {
    abstract fun check(part: Part): Outcome?

}

