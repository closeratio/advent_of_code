package com.closeratio.aoc2022.day23

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class WorldStateSimulationParserTest {

    @Autowired
    private lateinit var worldStateSimulationParser: WorldStateSimulationParser

    @Test
    fun parse_computeEmptyGroundTiles_returnsExpectedValue() {
        val result = worldStateSimulationParser
            .parse("/test_input.txt")
            .computeEmptyGroundTiles(10)

        assertThat(result).isEqualTo(110)
    }

    @Test
    fun parse_computeRoundsUntilFinished_returnsExpectedValue() {
        val result = worldStateSimulationParser
            .parse("/test_input.txt")
            .computeRoundsUntilFinished()

        assertThat(result).isEqualTo(20)
    }

}