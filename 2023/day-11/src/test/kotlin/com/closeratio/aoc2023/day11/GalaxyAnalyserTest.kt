package com.closeratio.aoc2023.day11

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class GalaxyAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var galaxyParser: GalaxyParser

    @Test
    fun sumShortestPaths_returnsExpectedValue() {
        val result = galaxyParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumShortestPaths()

        assertThat(result).isEqualTo(374)
    }

    @Test
    fun sumShortestPaths_largerExpansionFactor_returnsExpectedValue() {
        val result = galaxyParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumShortestPaths(10)

        assertThat(result).isEqualTo(1030)
    }

    @Test
    fun sumShortestPaths_muchLargerExpansionFactor_returnsExpectedValue() {
        val result = galaxyParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumShortestPaths(100)

        assertThat(result).isEqualTo(8410)
    }

}
