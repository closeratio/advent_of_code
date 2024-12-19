package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2024.day12.FenceCalculator.CalculationMethod.REGION_SIDES
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

    @Test
    fun calculateFencePriceSimple_RegionSides() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_1.txt"),
            REGION_SIDES
        )

        Assertions.assertThat(result).isEqualTo(80)
    }

    @Test
    fun calculateFencePriceModerate_RegionSides() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_2.txt"),
            REGION_SIDES
        )

        Assertions.assertThat(result).isEqualTo(436)
    }

    @Test
    fun calculateFencePriceAdvanced_RegionSides() {
        val result = fenceCalculator.calculateFencePrice(
            resourceLoader.loadResourceLines("/test_input_3.txt"),
            REGION_SIDES
        )

        Assertions.assertThat(result).isEqualTo(1206)
    }

}