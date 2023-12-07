package com.closeratio.aoc2023.day7

import com.closeratio.aoc2023.day7.CardType.JACK
import com.closeratio.aoc2023.day7.HandType.*

data class Hand(
    val cards: List<Card>,
    val specialJokerRule: Boolean
) : Comparable<Hand> {

    val type = computeHandType()

    private fun computeHandType(): HandType {
        val cardMap = cards
            .map(Card::cardType)
            .groupBy { it }
            .mapValues { (_, v) -> v.size }

        return if (specialJokerRule && JACK in cardMap) {
            // If the special joker rule is active and there's at least one Jack in the hand, then iterate through every
            // hand combination by swapping out all the Jacks for the same card (excluding Jacks).
            CardType
                .entries
                .filter { it != JACK }
                .map { type ->
                    Hand(
                        cards.map { card ->
                            if (card.cardType == JACK) Card(type.rank, type) else card
                        },
                        false
                    )
                }
                .maxBy { it.type.rank }
                .type
        } else {
            val maxValue = cardMap.values.max()
            when {
                maxValue == 5 -> FIVE_OF_A_KIND
                maxValue == 4 -> FOUR_OF_A_KIND
                maxValue == 3 && cardMap.size > 2 -> THREE_OF_A_KIND
                maxValue == 3 && cardMap.size == 2 -> FULL_HOUSE
                maxValue == 2 && cardMap.size == 3 -> TWO_PAIR
                maxValue == 2 && cardMap.size == 4 -> ONE_PAIR
                else -> HIGH_CARD
            }
        }
    }

    override fun compareTo(other: Hand): Int {
        val typeDiff = (type.rank - other.type.rank).toInt()
        if (typeDiff != 0) {
            return typeDiff
        }

        return cards.zip(other.cards)
            .map { (currCard, otherCard) -> (currCard.value - otherCard.value).toInt() }
            .firstOrNull { it != 0 } ?: 0
    }

}

