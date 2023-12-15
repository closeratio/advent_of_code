package com.closeratio.aoc2023.day14

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class RockSimulationTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var rockSimulationParser: RockSimulationParser

    @Test
    fun calculateLoad_returnsExpectedValue() {
        val result = rockSimulationParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).calculateLoad()

        assertThat(result).isEqualTo(136)
    }

}
