package com.closeratio.aoc2024.day1

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class LocationListProcessorTest {

    @Autowired
    private lateinit var locationListProcessor: LocationListProcessor

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun computeDistance() {
        val result = locationListProcessor.computeDistance(resourceLoader.loadResourceLines("/test_input.txt"))

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun computeSimilarityScore() {
        val result = locationListProcessor.computeSimilarityScore(resourceLoader.loadResourceLines("/test_input.txt"))

        assertThat(result).isEqualTo(31)
    }

}