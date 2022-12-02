package com.closeratio.aoc2022

import com.closeratio.aoc2022.common.CommonConfig
import com.closeratio.aoc2022.day2.GameParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [CommonConfig::class]
)
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
