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

}
