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

}
