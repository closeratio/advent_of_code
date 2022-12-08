package com.closeratio.aoc2022.day8

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ForestParserTest {

    @Autowired
    private lateinit var forestParser: ForestParser

    @Test
    fun parseForest_computeVisibleTreeCount_returnsExpectedValue() {
        val forest = forestParser.parseForest("/test_input.txt")
        val result = forest.computeVisibleTreeCount()

        assertThat(result).isEqualTo(21)
    }

    @Test
    fun parseForest_computeOptimalTreehouseScenicScore_returnsExpectedValue() {
        val forest = forestParser.parseForest("/test_input.txt")
        val result = forest.computeOptimalTreehouseScenicScore()

        assertThat(result).isEqualTo(8)
    }

}
