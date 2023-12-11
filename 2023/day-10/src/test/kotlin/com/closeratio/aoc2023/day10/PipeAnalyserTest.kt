package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class PipeAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var pipeParser: PipeParser

    @Test
    fun farthestDistanceFromStart_simpleInput_returnsExpectedValue() {
        val result = pipeParser.parse(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        ).farthestDistanceFromStart().value

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun farthestDistanceFromStart_complexInput_returnsExpectedValue() {
        val result = pipeParser.parse(
            resourceLoader.loadResourceLines("/test_input_2.txt")
        ).farthestDistanceFromStart().value

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun calculateTilesEnclosedByLoop_simpleInput_returnsExpectedValue() {
        val result = pipeParser.parse(
            resourceLoader.loadResourceLines("/test_input_3.txt")
        ).calculateTilesEnclosedByLoop()

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun calculateTilesEnclosedByLoop_complexInput_returnsExpectedValue() {
        val result = pipeParser.parse(
            resourceLoader.loadResourceLines("/test_input_4.txt")
        ).calculateTilesEnclosedByLoop()

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun calculateTilesEnclosedByLoop_anotherComplexInput_returnsExpectedValue() {
        val result = pipeParser.parse(
            resourceLoader.loadResourceLines("/test_input_5.txt")
        ).calculateTilesEnclosedByLoop()

        assertThat(result).isEqualTo(10)
    }

}