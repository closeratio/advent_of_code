package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class BeamAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var beamAnalyser: BeamAnalyser

    @Test
    fun computeEnergisedCount_returnsExpectedValue() {
        val result = beamAnalyser.computeEnergisedCount(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(46)
    }

    @Test
    fun computeEnergisedCount_realData_returnsExpectedValue() {
        val result = beamAnalyser.computeEnergisedCount(
            resourceLoader.loadResourceLines("/2023_day_16_input.txt"),
        )

        assertThat(result).isNotEqualTo(6187)
    }

}
