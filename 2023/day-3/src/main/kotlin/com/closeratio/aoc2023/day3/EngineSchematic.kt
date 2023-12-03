package com.closeratio.aoc2023.day3

import com.closeratio.aoc.common.math.Vec2

class EngineSchematic(
    private val characterMap: Map<Vec2, String>
) {

    private fun findParts(): Map<Vec2, String> = characterMap
        .filterValues { """[^0-9]""".toRegex().matches(it) }

    private fun getPartNumbers(partPos: Vec2): List<Long> {
        val adjacentNums = partPos.allAdjacent()
            .filter { it in characterMap }
            .toMutableSet()

        // Find left/right adjacent nums until the set doesn't grow anymore
        var growing = true
        while (growing) {
            val startSize = adjacentNums.size
            adjacentNums.addAll(adjacentNums
                .flatMap { setOf(it.left(), it.right()) }
                .filter { it in characterMap } // Has to be in the map
                .filter { it !in adjacentNums } // Can't be one we've already found
                .filter { it != partPos } // Can't be the part itself
            )
            val endSize = adjacentNums.size
            growing = startSize != endSize
        }

        val sortedNumbers = adjacentNums
            .sortedBy(Vec2::x)
            .sortedBy(Vec2::y)
        val partNumbers = mutableListOf<Long>()
        var currentNumString = StringBuilder()
        var previousX = sortedNumbers.first().x
        var previousY = sortedNumbers.first().y
        sortedNumbers.forEach {
            if (it.x - previousX > 1 || it.y != previousY) {
                // We're on a new number, so add to the list the number that was previously being built
                partNumbers.add(currentNumString.toString().toLong())
                currentNumString = StringBuilder()
            }

            currentNumString.append(characterMap.getValue(it))
            previousX = it.x
            previousY = it.y
        }
        partNumbers.add(currentNumString.toString().toLong())

        return partNumbers
    }

    fun gearRatioSum(): Long = findParts()
        .filterValues { it == "*" }
        .keys
        .map(::getPartNumbers)
        .filter { it.size == 2 }
        .map { it[0] * it[1] }
        .sum()

    fun partNumberSum(): Long = findParts()
        .keys
        .flatMap(::getPartNumbers)
        .sum()

}
