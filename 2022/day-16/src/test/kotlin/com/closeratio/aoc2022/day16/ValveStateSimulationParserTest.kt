package com.closeratio.aoc2022.day16

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ValveStateSimulationParserTest {

    @Autowired
    private lateinit var parser: ValveStateSimulationParser

    @Test
    fun parse_computeMaxPossiblePressure_returnsExpectedValue() {
        val sim = parser.parse("/test_input.txt")
        val result = sim.computeMaxPossiblePressure(30)

        assertThat(result).isEqualTo(1651)
    }

}
