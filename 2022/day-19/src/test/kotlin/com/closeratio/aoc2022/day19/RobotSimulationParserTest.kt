package com.closeratio.aoc2022.day19

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class RobotSimulationParserTest {

    @Autowired
    private lateinit var robotSimulationParser: RobotSimulationParser

    @Test
    fun parse_computeQualityLevelSum_returnsExpectedValue() {
        val result = robotSimulationParser
            .parse("/test_input.txt")
            .computeQualityLevelSum(24)

        assertThat(result).isEqualTo(33)
    }

    @Test
    fun parse_computeQualityLevelSumRealData_returnsExpectedValue() {
        val result = robotSimulationParser
            .parse("/2022_day_19_input.txt")
            .computeQualityLevelSum(24)

        assertThat(result).isEqualTo(1115)
    }

    @Test
    fun parse_computeGeodeProduct_returnsExpectedValue() {
        val result = robotSimulationParser
            .parse("/test_input.txt")
            .computeGeodeProduct(32)

        assertThat(result).isEqualTo(62 * 56)
    }

}
