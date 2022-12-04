package com.closeratio.aoc2022.day4

class AssignmentList(
    val assignmentPairs: List<Pair<SectionAssignment, SectionAssignment>>
) {

    fun computeFullyOverlappingPairs(): Int = assignmentPairs
        .filter { (first, second) -> first.contains(second) || second.contains(first) }
        .size

    fun computePartlyOverlappingPairs(): Int = assignmentPairs
        .filter { (first, second) -> first.overlaps(second) }
        .size

    companion object {

        private fun parseAssignment(assignmentString: String): SectionAssignment =
            SectionAssignment.from(assignmentString)

        private fun parseLine(line: String): Pair<SectionAssignment, SectionAssignment> = line
            .split(",")
            .let { (first, second) -> parseAssignment(first) to parseAssignment(second) }

        fun from(lines: List<String>): AssignmentList = lines
            .map(::parseLine)
            .let(::AssignmentList)
    }

}