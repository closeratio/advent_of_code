package com.closeratio.aoc2022.day25

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SnafuCalculatorTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun calculateSnafuNumber_givenTestInput_returnsExpectedValue() {
        val result = resourceLoader
            .loadResourceLines("/test_input.txt")
            .let(::SnafuCalculator)
            .calculateSnafuNumber()

        assertThat(result).isEqualTo("2=-1=0")
    }

}