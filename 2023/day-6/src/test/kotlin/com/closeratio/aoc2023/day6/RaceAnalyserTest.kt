package com.closeratio.aoc2023.day6

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class RaceAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var raceAnalyser: RaceAnalyser

    @Test
    fun computePossibleRecords_returnsExpectedValue() {
        val result = raceAnalyser.computePossibleRecords(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(288)
    }

    @Test
    fun computePossibleRecordsSingleRace_returnsExpectedValue() {
        val result = raceAnalyser.computePossibleRecords(
            resourceLoader.loadResourceLines("/test_input.txt"),
            true
        )

        assertThat(result).isEqualTo(71503)
    }

}
