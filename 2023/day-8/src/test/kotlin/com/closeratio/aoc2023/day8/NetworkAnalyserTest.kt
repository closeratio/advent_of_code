package com.closeratio.aoc2023.day8

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class NetworkAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var networkParser: NetworkParser

    @Test
    fun stepCount_firstTestInput_returnsExpectedValue() {
        val result = networkParser.parse(resourceLoader.loadResourceLines("/test_input_1.txt"))
            .stepCount()

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun stepCount_secondTestInput_returnsExpectedValue() {
        val result = networkParser.parse(resourceLoader.loadResourceLines("/test_input_2.txt"))
            .stepCount()

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun stepCountSimultaneous_thirdTestInput_returnsExpectedValue() {
        val result = networkParser.parse(resourceLoader.loadResourceLines("/test_input_3.txt"))
            .stepCountSimultaneous()

        assertThat(result).isEqualTo(6)
    }

}
