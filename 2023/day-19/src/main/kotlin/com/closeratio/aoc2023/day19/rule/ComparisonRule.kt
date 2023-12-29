package com.closeratio.aoc2023.day19.rule

import com.closeratio.aoc2023.day19.Part
import com.closeratio.aoc2023.day19.outcome.Outcome
import com.closeratio.aoc2023.day19.rule.Comparison.GREATER_THAN
import com.closeratio.aoc2023.day19.rule.Comparison.LESS_THAN
import intersect

data class ComparisonRule(
    private val partField: String,
    private val comparison: Comparison,
    private val value: Long,
    val outcome: Outcome
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

    override fun updateFilter(
        filter: Filter,
        getOutstandingRangeFilter: () -> Filter,
        updateOutsideRangeFilter: (Filter) -> Unit
    ): Filter {

        return when (partField) {
            "x" -> {
                val adjusted = adjustRange(filter.xRange)
                updateOutsideRangeFilter(
                    getOutstandingRangeFilter().copy(
                        xRange = excludedRange(filter.xRange)
                    )
                )
                filter.copy(xRange = adjusted)
            }

            "m" -> {
                val adjusted = adjustRange(filter.mRange)
                updateOutsideRangeFilter(
                    getOutstandingRangeFilter().copy(
                        mRange = excludedRange(filter.mRange)
                    )
                )
                filter.copy(mRange = adjusted)
            }

            "a" -> {
                val adjusted = adjustRange(filter.aRange)
                updateOutsideRangeFilter(
                    getOutstandingRangeFilter().copy(
                        aRange = excludedRange(filter.aRange)
                    )
                )
                filter.copy(aRange = adjusted)
            }

            "s" -> {
                val adjusted = adjustRange(filter.sRange)
                updateOutsideRangeFilter(
                    getOutstandingRangeFilter().copy(
                        sRange = excludedRange(filter.sRange)
                    )
                )
                filter.copy(sRange = adjusted)
            }

            else -> {
                throw IllegalArgumentException("Unknown part field name: $partField")
            }
        }
    }

    private fun adjustRange(
        currRange: LongRange
    ): LongRange = currRange.intersect(
        when (comparison) {
            GREATER_THAN -> (value + 1)..4000
            LESS_THAN -> 1..<value
        }
    )

    private fun excludedRange(
        currRange: LongRange
    ): LongRange = currRange.intersect(
        when (comparison) {
            GREATER_THAN -> 1..value
            LESS_THAN -> value..4000
        }
    )
}

