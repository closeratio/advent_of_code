package com.closeratio.aoc2022.day4

data class SectionAssignment(
    val range: IntRange
) {

    private val rangeSet = range.toSet()

    fun contains(other: SectionAssignment): Boolean = other.range.first >= range.first && other.range.last <= range.last
    fun overlaps(other: SectionAssignment): Boolean = rangeSet.intersect(other.rangeSet).isNotEmpty()

    companion object {
        fun from(assignmentString: String): SectionAssignment = assignmentString
            .split("-")
            .let { (first, second) -> SectionAssignment(first.toInt()..second.toInt()) }
    }

}
