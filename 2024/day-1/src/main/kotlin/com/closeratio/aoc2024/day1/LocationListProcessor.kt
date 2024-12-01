package com.closeratio.aoc2024.day1

import org.springframework.stereotype.Service
import kotlin.math.absoluteValue

@Service
class LocationListProcessor {

    fun computeDistance(
        input: List<String>
    ): Long {
        val pairs = input
            .map { line -> line.split("   ") }
            .map { (lString, rString) -> lString.toLong() to rString.toLong() }

        val leftList = pairs
            .map { it.first }
            .sorted()

        val rightList = pairs
            .map { it.second }
            .sorted()

        return leftList.zip(rightList)
            .map { (left, right) -> (left - right).absoluteValue }
            .sum()
    }

}