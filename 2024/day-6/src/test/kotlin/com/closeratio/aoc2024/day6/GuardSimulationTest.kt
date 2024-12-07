package com.closeratio.aoc2024.day6

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class GuardSimulationTest {

    @Autowired
    private lateinit var guardSimulation: GuardSimulation

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun countDistinctPositions() {
        val result = guardSimulation.countDistinctPositions(resourceLoader.loadResourceLines("/test_input_1.txt"))

        assertThat(result).isEqualTo(41)
    }

    @Test
    fun countObstructionPositionsForLoop() {
        val result =
            guardSimulation.countObstructionPositionsForLoop(resourceLoader.loadResourceLines("/test_input_1.txt"))

        assertThat(result).isEqualTo(6)
    }

}