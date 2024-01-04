package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.outcome.TerminalOutcome

class TerminalRule(
    val accept: Boolean
) : Rule() {

    override fun check(part: Part): Outcome = TerminalOutcome(accept)
}

