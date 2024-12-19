package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class FenceCalculatorTest {

    @Autowired
    private lateinit var fenceCalculator: FenceCalculator

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun calculateFencePriceSimple() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        Assertions.assertThat(result).isEqualTo(140)
    }

    @Test
    fun calculateFencePriceModerate() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_2.txt")
        )

        Assertions.assertThat(result).isEqualTo(772)
    }

    @Test
    fun calculateFencePriceAdvanced() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_3.txt")
        )

        Assertions.assertThat(result).isEqualTo(1930)
    }

}