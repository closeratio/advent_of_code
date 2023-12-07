package com.closeratio.aoc2023.day7

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc2023.day7.HandType.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class HandTest {

    @Autowired
    private lateinit var handParser: HandParser

    companion object {
        @JvmStatic
        fun hands(): List<Arguments> = listOf(
            Arguments.of("32T3K", ONE_PAIR),
            Arguments.of("T55J5", THREE_OF_A_KIND),
            Arguments.of("KK677", TWO_PAIR),
            Arguments.of("KTJJT", TWO_PAIR),
            Arguments.of("QQQJA", THREE_OF_A_KIND),
        )

        @JvmStatic
        fun handsSpecialJokerRule(): List<Arguments> = listOf(
            Arguments.of("32T3K", ONE_PAIR),
            Arguments.of("T55J5", FOUR_OF_A_KIND),
            Arguments.of("KK677", TWO_PAIR),
            Arguments.of("KTJJT", FOUR_OF_A_KIND),
            Arguments.of("QQQJA", FOUR_OF_A_KIND),
        )
    }

    @ParameterizedTest
    @MethodSource("hands")
    fun givenHand_hasExpectedType(
        handString: String,
        expectedType: HandType
    ) {
        val hand = handParser.parse(handString, false)

        assertThat(hand.type).isEqualTo(expectedType)
    }

    @ParameterizedTest
    @MethodSource("handsSpecialJokerRule")
    fun givenHand_specialJokerRule_hasExpectedType(
        handString: String,
        expectedType: HandType
    ) {
        val hand = handParser.parse(handString, true)

        assertThat(hand.type).isEqualTo(expectedType)
    }

}
