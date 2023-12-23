package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class HeatLossRoutePlannerTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var heatLossRoutePlanner: HeatLossRoutePlanner

    @Test
    fun calculateMinimalHeatLoss_returnsExpectedValue() {
        val result = heatLossRoutePlanner.calculateMinimalHeatLoss(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(102)
    }

    @Test
    fun calculateMinimalHeatLoss_ultraCrucible_returnsExpectedValue() {
        val result = heatLossRoutePlanner.calculateMinimalHeatLoss(
            resourceLoader.loadResourceLines("/test_input_1.txt"),
            4,
            10
        )

        assertThat(result).isEqualTo(94)
    }

    @Test
    fun calculateMinimalHeatLoss_ultraCrucibleDifferentInput_returnsExpectedValue() {
        val result = heatLossRoutePlanner.calculateMinimalHeatLoss(
            resourceLoader.loadResourceLines("/test_input_2.txt"),
            4,
            10
        )

        assertThat(result).isEqualTo(71)
    }

}
