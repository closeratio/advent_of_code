package com.closeratio.aoc2024.day3

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MemoryScannerTest {

    @Autowired
    private lateinit var memoryScanner: MemoryScanner

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun sumMulInstructions() {
        val result = memoryScanner.sumMulInstructions(resourceLoader.loadResourceText("/test_input_1.txt"))

        assertThat(result).isEqualTo(161)
    }

    @Test
    fun sumEnabledMulInstructions() {
        val result = memoryScanner.sumEnabledMulInstructions(resourceLoader.loadResourceText("/test_input_2.txt"))

        assertThat(result).isEqualTo(48)
    }

}