package com.closeratio.aoc2023.day6

import org.springframework.stereotype.Component

@Component
class RaceAnalyser {

    private fun parseRecords(
        input: List<String>,
        concatenate: Boolean
    ): List<RaceRecord> = input
        .map { it.split(":")[1] }
        .map(String::trim)
        .map { it.split("""\s+""".toRegex()) }
        .let { (times, distances) ->
            if (concatenate) {
                listOf(times.joinToString("") to distances.joinToString(""))
            } else {
                times.zip(distances)
            }
        }
        .map { (timeString, distanceString) -> RaceRecord(timeString.toLong(), distanceString.toLong()) }

    private fun computeStrategyCount(
        record: RaceRecord
    ): Long = (1..<record.time)
        .map { time -> Race(time) }
        .map { it.distance(record.time) }
        .filter { it > record.distance }
        .size
        .toLong()

    fun computePossibleRecords(
        input: List<String>,
        concatenate: Boolean = false
    ): Long = parseRecords(input, concatenate)
        .map(::computeStrategyCount)
        .reduce { acc, curr -> acc * curr }

}
