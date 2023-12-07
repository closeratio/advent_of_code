package com.closeratio.aoc2023.day7

import org.springframework.stereotype.Component

@Component
class HandAnalyser(
    private val handParser: HandParser
) {

    private fun parseHandsAndBids(
        input: List<String>,
        specialJokerRule: Boolean
    ): List<HandAndBid> = input
        .map { it.split(" ") }
        .map { (handString, bidString) -> handParser.parse(handString, specialJokerRule) to bidString.toLong() }
        .map { (hand, bid) -> HandAndBid(hand, bid) }

    fun computeTotalWinnings(
        input: List<String>,
        specialJokerRule: Boolean = false
    ): Long {
        val handsAndBids = parseHandsAndBids(input, specialJokerRule)

        return handsAndBids
            .sortedWith { left, right -> left.hand.compareTo(right.hand) }
            .mapIndexed { index, handAndBid -> handAndBid.bid * (index + 1) }
            .sum()
    }

}
