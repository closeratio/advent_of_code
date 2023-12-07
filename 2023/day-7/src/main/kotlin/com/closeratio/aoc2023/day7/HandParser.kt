package com.closeratio.aoc2023.day7

import com.closeratio.aoc2023.day7.CardType.*
import org.springframework.stereotype.Component

@Component
class HandParser {
    fun parse(
        line: String,
        specialJokerRule: Boolean
    ): Hand = line
        .map { char ->
            when (char) {
                'A' -> ACE
                'K' -> KING
                'Q' -> QUEEN
                'J' -> JACK
                'T' -> TEN
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                else -> throw IllegalArgumentException("Unhandled type: $char")
            }
        }
        .let { types ->
            Hand(
                types.map {
                    Card(
                        if (it == JACK && specialJokerRule) 0L else it.rank,
                        it
                    )
                },
                specialJokerRule
            )
        }
}
