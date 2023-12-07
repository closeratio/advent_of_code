package com.closeratio.aoc2023.day7

import com.closeratio.aoc.common.AocTest
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
            Arguments.of("32T3K", HandType.ONE_PAIR),
            Arguments.of("T55J5", HandType.THREE_OF_A_KIND),
            Arguments.of("KK677", HandType.TWO_PAIR),
            Arguments.of("KTJJT", HandType.TWO_PAIR),
            Arguments.of("QQQJA", HandType.THREE_OF_A_KIND),
        )
    }

    @ParameterizedTest
    @MethodSource("hands")
    fun givenHand_hasExpectedType(
        handString: String,
        expectedType: HandType
    ) {
        val hand = handParser.parse(handString)

        assertThat(hand.type).isEqualTo(expectedType)
    }

}
