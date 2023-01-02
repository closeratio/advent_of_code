package com.closeratio.aoc2022.day21

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MonkeyTreeParserTest {

    @Autowired
    private lateinit var parser: MonkeyTreeParser

    @Test
    fun parse_computeNumber_returnsExpectedValue() {
        val result = parser
            .parse("/test_input.txt")
            .computeRootNumber()

        assertThat(result).isEqualTo(152)
    }

    @Test
    fun parse_computeRequiredHumanNumber_returnsExpectedValue() {
        val result = parser
            .parse("/test_input.txt")
            .computeRequiredHumanNumber()

        assertThat(result).isEqualTo(301)
    }

}
