package com.closeratio.aoc2023.day9

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SequenceAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var sequenceParser: SequenceParser

    @Test
    fun sumNextValues_returnsExpectedValue() {
        val result = sequenceParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumNextValues()

        assertThat(result).isEqualTo(114)
    }

    @Test
    fun sumPreviousValues_returnsExpectedValue() {
        val result = sequenceParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumPreviousValues()

        assertThat(result).isEqualTo(2)
    }

}