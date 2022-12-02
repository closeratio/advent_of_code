package com.closeratio.aoc2022

import com.closeratio.aoc2022.day2.RockPaperScissorEnum.*
import com.closeratio.aoc2022.day2.RockPaperScissorEnum.Companion.decodePlayPair
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RockPaperScissorEnumTest {

    @Test
    fun decodePlayPair_givenInput_decodesAsExpected() {
        assertThat(decodePlayPair("A Y")).isEqualTo(ROCK to PAPER)
        assertThat(decodePlayPair("B X")).isEqualTo(PAPER to ROCK)
        assertThat(decodePlayPair("C Z")).isEqualTo(SCISSORS to SCISSORS)
    }

}
