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

        assertThat(result).isEqualTo(10605)
    }

}
