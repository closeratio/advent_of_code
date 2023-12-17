package com.closeratio.aoc2023.day15

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class HashGeneratorTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var hashGenerator: HashGenerator

    @Test
    fun generateHash_simpleInput_returnsExpectedValue() {
        val result = hashGenerator.generateHash("HASH")

        assertThat(result).isEqualTo(52)
    }

    @Test
    fun generateHash_testInput_returnsExpectedValue() {
        val result = hashGenerator.generateHash(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(1320)
    }

}