package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class DigAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var digAnalyser: DigAnalyser

    @Test
    fun computeDigArea_returnsExpectedValue() {
        val result = digAnalyser.computeDigArea(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(62)
    }

    @Test
    fun computeDigArea_realData_returnsExpectedValue() {
        val result = digAnalyser.computeDigArea(
            resourceLoader.loadResourceLines("/2023_day_18_input.txt")
        )

        assertThat(result).isEqualTo(47675)
    }

    @Test
    fun computeDigArea_hexString_returnsExpectedValue() {
        val result = digAnalyser.computeDigArea(
            resourceLoader.loadResourceLines("/test_input.txt"),
            true
        )

        assertThat(result).isEqualTo(952_408_144_115)
    }

}
