package com.closeratio.aoc2023.day7

import org.springframework.stereotype.Component

@Component
class HandParser {
    fun parse(line: String): Hand = line
        .map { char ->
            when (char) {
                'A' -> CardType.ACE
                'K' -> CardType.KING
                'Q' -> CardType.QUEEN
                'J' -> CardType.JACK
                'T' -> CardType.TEN
                '9' -> CardType.NINE
                '8' -> CardType.EIGHT
                '7' -> CardType.SEVEN
                '6' -> CardType.SIX
                '5' -> CardType.FIVE
                '4' -> CardType.FOUR
                '3' -> CardType.THREE
                '2' -> CardType.TWO
                else -> throw IllegalArgumentException("Unhandled type: $char")
            }
        }
        .let(::Hand)
}
