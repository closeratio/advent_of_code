package com.closeratio.aoc2024.day10

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class TrailheadAnalyserTest {

    @Autowired
    private lateinit var trailheadAnalyser: TrailheadAnalyser

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun sumTrailheadScores_input1() {
        val result = trailheadAnalyser.sumTrailheadScores(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun sumTrailheadScores_input2() {
        val result = trailheadAnalyser.sumTrailheadScores(
            resourceLoader.loadResourceLines("/test_input_2.txt")
        )

        assertThat(result).isEqualTo(36)
    }

    @Test
    fun sumTrailheadRatings_input2() {
        val result = trailheadAnalyser.sumTrailheadRatings(
            resourceLoader.loadResourceLines("/test_input_2.txt")
        )

        assertThat(result).isEqualTo(81)
    }
}