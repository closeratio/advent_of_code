package com.closeratio.aoc2023.day4

import org.springframework.stereotype.Component

@Component
class ScratchcardAnalyser {

    private fun parseScratchcards(lines: List<String>): List<Scratchcard> = lines
        .map {
            val id = """Card\s+(\d+)""".toRegex().find(it)!!.groupValues[1].toLong()

            val winningNumbersString = it.split(":")[1]
                .split("|")[0]
                .trim()

            val heldNumbersString = it.split(":")[1]
                .split("|")[1]
                .trim()

            Scratchcard(
                id,
                winningNumbersString.split("\\s+".toRegex()).map(String::toLong).toSet(),
                heldNumbersString.split("\\s+".toRegex()).map(String::toLong).toSet()
            )
        }

    fun sumScratchcardValues(
        lines: List<String>
    ): Long = parseScratchcards(lines)
        .map(Scratchcard::calculateValue)
        .sum()

    fun sumTotalScratchcards(
        lines: List<String>
    ): Long {
        val scratchcards = parseScratchcards(lines).associateBy(Scratchcard::id)
        val totalCache = mutableMapOf<Long, Long>()
        return scratchcards
            .values
            .map { it.calculateScratchcardCount(scratchcards, totalCache) }
            .sum()
    }
}

