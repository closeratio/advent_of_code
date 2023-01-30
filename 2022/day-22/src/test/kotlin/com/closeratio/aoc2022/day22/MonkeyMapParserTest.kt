package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MonkeyMapParserTest {

    @Autowired
    private lateinit var monkeyMapParser: MonkeyMapParser

    @Test
    fun parse_computePassword_returnsExpectedValue() {
        val result = monkeyMapParser
            .parse("/test_input.txt")
            .computePassword()

        assertThat(result).isEqualTo(6032)
    }

    @Test
    fun parseAdvancedRealData_computePassword_returnsExpectedValue() {
        val result = monkeyMapParser
            .parse("/2022_day_22_input.txt", false)
            .computePassword()

        assertThat(result).isEqualTo(6032)
    }

}
