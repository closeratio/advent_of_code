package com.closeratio.aoc2022.day9

data class Rope(
    val ropeLinks: List<RopeLink>
) {

    fun move(direction: Direction): Rope {
        val newHeadPos = direction.vectorTransform(ropeLinks.first().head)

        val movedHead = ropeLinks.first().move(newHeadPos)

        val newLinks = ropeLinks
            .drop(1)
            .fold(listOf(movedHead)) { acc, curr ->
                acc + curr.move(acc.last().tail)
            }

        return Rope(newLinks)
    }

}

