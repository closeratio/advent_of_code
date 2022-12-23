package com.closeratio.aoc2022.day17

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class GameStateSimulationParserTest {

    @Autowired
    private lateinit var parser: GameStateSimulationParser

    @Test
    fun parse_simulateUntilTotalRocksFallen_returnsExpectedValue() {
        val sim = parser.parse("/test_input.txt")
        val result = sim.simulateUntilTotalRocksFallen(2022)

        assertThat(result).isEqualTo(3068)
    }

    @Test
    fun parse_simulateUntilTotalRocksFallenRealData_returnsExpectedValue() {
        val sim = parser.parse("/2022_day17_input.txt")
        val result = sim.simulateUntilTotalRocksFallen(2022)

        assertThat(result).isEqualTo(3171)
    }

    @Test
    fun parse_simulateUntilTotalRocksFallenManyIterations_returnsExpectedValue() {
        val sim = parser.parse("/test_input.txt")
        val result = sim.simulateUntilTotalRocksFallen(1_000_000_000_000L)

        assertThat(result).isEqualTo(1_514_285_714_288L)
    }

    @Test
    fun parse_simulateUntilTotalRocksFallenManyIterationsRealData_returnsExpectedValue() {
        val sim = parser.parse("/2022_day17_input.txt")
        val result = sim.simulateUntilTotalRocksFallen(1_000_000_000_000L)

        assertThat(result).isGreaterThan(3171)
    }

}
