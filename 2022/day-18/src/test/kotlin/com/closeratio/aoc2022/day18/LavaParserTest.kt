package com.closeratio.aoc2022.day18

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class LavaParserTest {

    @Autowired
    private lateinit var lavaParser: LavaParser

    @Test
    fun parseLava_computeSurfaceArea_returnsExpectedValue() {
        val lava = lavaParser.parseLava("/test_input.txt")
        val result = lava.computeSurfaceArea()

        assertThat(result).isEqualTo(64)
    }

    @Test
    fun parseLava_computeExteriorSurfaceArea_returnsExpectedValue() {
        val lava = lavaParser.parseLava("/test_input.txt")
        val result = lava.computeExteriorSurfaceArea()

        assertThat(result).isEqualTo(58)
    }

}
