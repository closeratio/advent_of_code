package com.closeratio.aoc2024.day5

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class PrintQueueValidatorTest {

    @Autowired
    private lateinit var printQueueValidator: PrintQueueValidator

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun sumMiddlePageNumbersValid() {
        val result = printQueueValidator.sumMiddlePageNumbers(
            resourceLoader.loadResourceText("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(143)
    }

    @Test
    fun sumMiddlePageNumbersInvalid() {
        val result = printQueueValidator.sumMiddlePageNumbers(
            resourceLoader.loadResourceText("/test_input_1.txt"),
            false
        )

        assertThat(result).isEqualTo(123)
    }

}