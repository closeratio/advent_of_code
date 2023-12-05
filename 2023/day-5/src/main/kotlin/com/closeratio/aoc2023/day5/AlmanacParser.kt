package com.closeratio.aoc2023.day5

import org.springframework.stereotype.Component

@Component
class AlmanacParser {
    private fun parseSeedRanges(
        line: String,
        useRanges: Boolean
    ): List<LongRange> {
        val numbers = line
            .split(":")[1]
            .trim()
            .split(" ")
            .map(String::trim)
            .map(String::toLong)

        return if (useRanges) {
            numbers.windowed(2, 2)
                .map { (start, length) -> start..<(start + length) }
        } else {
            numbers.map { it..it }
        }
    }

    private fun parseMapping(text: String): Mapping = text
        .split("\n")
        .drop(1)
        .map {
            val (targetStart, sourceStart, length) = it
                .split(" ")
                .map(String::toLong)

            Range(
                sourceStart..<(sourceStart + length),
                targetStart..<(targetStart + length)
            )
        }
        .let(::Mapping)

    fun parseAlmanac(
        input: String,
        useRanges: Boolean
    ): Almanac {
        val chunks = input.split("\n\n")
        val seeds = parseSeedRanges(chunks[0], useRanges)

        return Almanac(
            seeds,
            parseMapping(chunks[1]),
            parseMapping(chunks[2]),
            parseMapping(chunks[3]),
            parseMapping(chunks[4]),
            parseMapping(chunks[5]),
            parseMapping(chunks[6]),
            parseMapping(chunks[7])
        )
    }

}
