package com.closeratio.aoc2023.day7

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class HandAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var handAnalyser: HandAnalyser

    @Test
    fun computeTotalWinnings_returnsExpectedValue() {
        val result = handAnalyser.computeTotalWinnings(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(6440)
    }

}
