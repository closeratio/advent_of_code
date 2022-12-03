package com.closeratio.aoc.app

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class GameParserTest {

    @Autowired
    private lateinit var gameParser: GameParser

    @Test
    fun computePlayPairPoints_givenTestInput_computesExpectedResult() {
        val result = gameParser.computePlayPairPoints("/test_input.txt")
        assertThat(result).isEqualTo(15)
    }

    @Test
    fun computePlayResultPairPoints_givenTestInput_computesExpectedResult() {
        val result = gameParser.computePlayResultPairPoints("/test_input.txt")
        assertThat(result).isEqualTo(12)
    }

}
