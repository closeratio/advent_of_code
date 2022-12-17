package com.closeratio.aoc2022.day14

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SandSimulationParserTest {

    @Autowired
    private lateinit var sandSimulationParser: SandSimulationParser

    @Test
    fun parseSandSimulation_simulateUntilFinished_returnsExpectedValue() {
        val result = sandSimulationParser
            .parseSandSimulation("/test_input.txt")
            .simulateUntilFinished()

        assertThat(result).isEqualTo(24)
    }

    @Test
    fun parseSandSimulation_simulateUntilFinished_returnsExpectedValueForRealData() {
        val result = sandSimulationParser
            .parseSandSimulation("/2022_day_14_input.txt")
            .simulateUntilFinished()

        assertThat(result).isEqualTo(858)
    }

    @Test
    fun parseSandSimulation_simulateUntilFinishedWithFloor_returnsExpectedValue() {
        val result = sandSimulationParser
            .parseSandSimulation("/test_input.txt")
            .simulateUntilFinished(true)

        assertThat(result).isEqualTo(93)
    }

}
