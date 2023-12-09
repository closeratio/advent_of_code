package com.closeratio.aoc2023.day9

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SeequenceAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var sequenceParser: SequenceParser

    @Test
    fun sumExtrapolatedValues_returnsExpectedValue() {
        val result = sequenceParser.parse(
            resourceLoader.loadResourceLines("/test_input.txt")
        ).sumExtrapolatedValues()

        assertThat(result).isEqualTo(114)
    }

}