package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.rule.Comparison.GREATER_THAN
import com.closeratio.aoc2023.day19.rule.Comparison.LESS_THAN

data class ComparisonRule(
    private val partField: String,
    private val comparison: Comparison,
    private val value: Long,
    private val outcome: Outcome
) : Rule() {

    override fun check(part: Part): Outcome? {
        val fieldValue = when (partField) {
            "x" -> part.x
            "m" -> part.m
            "a" -> part.a
            "s" -> part.s
            else -> throw IllegalArgumentException("Unknown part field name: $partField")
        }

        val passesCheck = when (comparison) {
            GREATER_THAN -> fieldValue > value
            LESS_THAN -> fieldValue < value
        }

        return if (passesCheck) outcome else null
    }
}

