package com.closeratio.aoc2024.day4

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class XmasCounterTest {

    @Autowired
    private lateinit var xmasCounter: XmasCounter

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun countXmas() {
        val result = xmasCounter.countXmas(resourceLoader.loadResourceLines("/test_input_1.txt"))

        assertThat(result).isEqualTo(18)
    }

    @Test
    fun countXShapedMas() {
        val result = xmasCounter.countXShapedMas(resourceLoader.loadResourceLines("/test_input_1.txt"))

        assertThat(result).isEqualTo(9)
    }

}