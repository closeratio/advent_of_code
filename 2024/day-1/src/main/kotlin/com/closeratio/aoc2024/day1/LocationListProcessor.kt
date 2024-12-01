package com.closeratio.aoc2024.day1

import org.springframework.stereotype.Service
import kotlin.math.absoluteValue

@Service
class LocationListProcessor {

    fun computeDistance(
        input: List<String>
    ): Long {
        val (leftList, rightList) = getLists(input)

        return leftList.zip(rightList)
            .map { (left, right) -> (left - right).absoluteValue }
            .sum()
    }

    fun computeSimilarityScore(
        input: List<String>
    ): Long {
        val (leftList, rightList) = getLists(input)

        val occurrenceMap = rightList
            .groupBy { it }
            .mapValues { it.value.size.toLong() }

        return leftList
            .map { it * occurrenceMap.getOrDefault(it, 0L) }
            .sum()
    }

    private fun getLists(
        input: List<String>
    ): Pair<List<Long>, List<Long>> {
        val pairs = input
            .map { line -> line.split("   ") }
            .map { (lString, rString) -> lString.toLong() to rString.toLong() }

        val leftList = pairs
            .map { it.first }
            .sorted()

        val rightList = pairs
            .map { it.second }
            .sorted()

        return leftList to rightList
    }

}