package com.closeratio.aoc2024.day11

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class StoneProcessorTest {

    @Autowired
    private lateinit var stoneProcessor: StoneProcessor

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun blink_6_times() {
        val result = stoneProcessor.blink(
            resourceLoader.loadResourceText("/test_input_1.txt"),
            6
        )

        assertThat(result).isEqualTo(22)
    }

    @Test
    fun blink_25_times() {
        val result = stoneProcessor.blink(
            resourceLoader.loadResourceText("/test_input_1.txt"),
            25
        )

        assertThat(result).isEqualTo(55312)
    }

}