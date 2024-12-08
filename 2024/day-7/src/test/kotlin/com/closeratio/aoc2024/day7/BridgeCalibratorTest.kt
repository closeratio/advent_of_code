package com.closeratio.aoc2024.day7

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class BridgeCalibratorTest {

    @Autowired
    private lateinit var bridgeCalibrator: BridgeCalibrator

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun calibrate() {
        val result = bridgeCalibrator.calibrate(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(3749)
    }

}