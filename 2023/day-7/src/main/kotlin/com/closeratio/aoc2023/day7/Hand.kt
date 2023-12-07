package com.closeratio.aoc2023.day7

import com.closeratio.aoc2023.day7.HandType.*

data class Hand(
    val cards: List<CardType>
) : Comparable<Hand> {

    val type = computeHandType()

    private fun computeHandType(): HandType {
        val cardMap = cards
            .groupBy { it }
            .mapValues { (_, v) -> v.size }

        val maxValue = cardMap.values.max()
        return when {
            maxValue == 5 -> FIVE_OF_A_KIND
            maxValue == 4 -> FOUR_OF_A_KIND
            maxValue == 3 && cardMap.size > 2 -> THREE_OF_A_KIND
            maxValue == 3 && cardMap.size == 2 -> FULL_HOUSE
            maxValue == 2 && cardMap.size == 3 -> TWO_PAIR
            maxValue == 2 && cardMap.size == 4 -> ONE_PAIR
            else -> HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        val typeDiff = (type.rank - other.type.rank).toInt()
        if (typeDiff != 0) {
            return typeDiff
        }

        return cards.zip(other.cards)
            .map { (currCard, otherCard) -> (currCard.rank - otherCard.rank).toInt() }
            .firstOrNull { it != 0 } ?: 0
    }

}

