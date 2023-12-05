package com.closeratio.aoc2023.day5

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class AlmanacTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var almanacParser: AlmanacParser

    @Test
    fun computeLowestLocationNumber_returnsExpectedResult() {
        val result = almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/test_input.txt"), false)
            .computeLowestLocationNumber()

        assertThat(result).isEqualTo(35)
    }

    @Test
    fun computeLowestLocationNumber_actualData_returnsExpectedResult() {
        val result = almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/2023_day_5_input.txt"), false)
            .computeLowestLocationNumber()

        assertThat(result).isEqualTo(388071289)
    }

    @Test
    fun reverseSearch_returnsExpectedResult() {
        val result = almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/test_input.txt"), false)
            .reverseSearch(35)

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun computeLowestLocationNumber_withRanges_returnsExpectedResult() {
        val result = almanacParser
            .parseAlmanac(resourceLoader.loadResourceText("/test_input.txt"), true)
            .computeLowestLocationNumber()

        assertThat(result).isEqualTo(46)
    }

}
