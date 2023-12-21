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
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(102)
    }

}
