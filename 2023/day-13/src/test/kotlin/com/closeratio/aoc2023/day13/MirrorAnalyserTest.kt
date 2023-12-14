package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MirrorAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var mirrorAnalyser: MirrorAnalyser

    @Test
    fun summarise_returnsExpectedValue() {
        val result = mirrorAnalyser.summarise(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(405)
    }

    @Test
    fun summarise_actualData_returnsExpectedValue() {
        val result = mirrorAnalyser.summarise(
            resourceLoader.loadResourceText("/2023_day_13_input.txt")
        )

        assertThat(result).isEqualTo(31877)
    }

    @Test
    fun summariseWithoutSmudge_returnsExpectedValue() {
        val result = mirrorAnalyser.summariseWithoutSmudge(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(400)
    }

    @Test
    fun summariseWithoutSmudge_actualData_returnsExpectedValue() {
        val result = mirrorAnalyser.summariseWithoutSmudge(
            resourceLoader.loadResourceText("/2023_day_13_input.txt")
        )

        assertThat(result).isEqualTo(42996)
    }

}
