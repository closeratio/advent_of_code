package com.aoc2022.day11

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc2022.day11.MonkeyParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MonkeyParserTest {

    @Autowired
    private lateinit var monkeyParser: MonkeyParser

    @Test
    fun parseInput_givenTestInputAndSimulate20Rounds_returnsExpectedValue() {
        val result = monkeyParser
            .parseInput("/test_input.txt")
            .calculateMonkeyBusiness(20)

        assertThat(result).isEqualTo(10_605)
    }

    @Test
    fun parseInput_givenTestInputAndSimulate1000RoundsWithoutDecreasedWorryLevel_returnsExpectedValue() {
        val result = monkeyParser
            .parseInput("/test_input.txt", false)
            .calculateMonkeyBusiness(1_000)

        assertThat(result).isEqualTo(5204L * 5192L)
    }

    @Test
    fun parseInput_givenTestInputAndSimulate5000RoundsWithoutDecreasedWorryLevel_returnsExpectedValue() {
        val result = monkeyParser
            .parseInput("/test_input.txt", false)
            .calculateMonkeyBusiness(5_000)

        assertThat(result).isEqualTo(26075L * 26000L)
    }

    @Test
    fun parseInput_givenTestInputAndSimulate10000RoundsWithoutDecreasedWorryLevel_returnsExpectedValue() {
        val result = monkeyParser
            .parseInput("/test_input.txt", false)
            .calculateMonkeyBusiness(10_000)

        assertThat(result).isEqualTo(2_713_310_158L)
    }

}
