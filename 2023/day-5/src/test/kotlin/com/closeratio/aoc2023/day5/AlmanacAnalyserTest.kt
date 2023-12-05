package com.closeratio.aoc2023.day5

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class AlmanacAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var almanacAnalyser: AlmanacAnalyser

    @Test
    fun computeLowestLocationNumber_returnsExpectedResult() {
        val result = almanacAnalyser.computeLowestLocationNumber(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(35)
    }

}
