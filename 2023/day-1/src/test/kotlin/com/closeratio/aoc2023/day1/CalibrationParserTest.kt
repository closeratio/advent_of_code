package com.closeratio.aoc2023.day1

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class CalibrationParserTest {

    @Autowired
    private lateinit var calibrationParser: CalibrationParser

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun sumCalibrationValuesBasic_returnsExpectedValues() {
        val result = calibrationParser.sumCalibrationValues(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(142)
    }

    @Test
    fun sumCalibrationValuesAdvanced_returnsExpectedValues() {
        val result = calibrationParser.sumCalibrationValues(
            resourceLoader.loadResourceLines("/test_input_2.txt"),
            true
        )

        assertThat(result).isEqualTo(281)
    }


}
