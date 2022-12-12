package com.closeratio.aoc2022.day12

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class HeightMapParserTest {

    @Autowired
    private lateinit var heightMapParser: HeightMapParser

    @Test
    fun parseHeightMap_computePathLength_returnsExpectedValue() {
        val result = heightMapParser
            .parseHeightMap("/test_input.txt")
            .computePathLength()

        assertThat(result).isEqualTo(31)
    }

    @Test
    fun parseHeightMap_shortestRouteStartingFrom_returnsExpectedValue() {
        val result = heightMapParser
            .parseHeightMap("/test_input.txt")
            .shortestRouteStartingFrom("a")

        assertThat(result).isEqualTo(29)
    }

}
