package com.closeratio.aoc2022.day15

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SensorStateParserTest {

    @Autowired
    private lateinit var beaconStateParser: SensorStateParser

    @Test
    fun parseSensorState_invalidBeaconPositions_returnsExpectedResult() {
        val state = beaconStateParser.parseSensorState("/test_input.txt")
        val result = state.invalidBeaconPositions(10)

        assertThat(result).isEqualTo(26)
    }

}
