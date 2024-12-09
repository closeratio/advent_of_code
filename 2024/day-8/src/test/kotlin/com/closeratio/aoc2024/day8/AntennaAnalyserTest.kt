package com.closeratio.aoc2024.day8

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class AntennaAnalyserTest {

    @Autowired
    private lateinit var antennaAnalyser: AntennaAnalyser

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun countAntinodes() {
        val result = antennaAnalyser.countAntinodes(
            resourceLoader.loadResourceLines("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(14)
    }

    @Test
    fun countAntinodesWithResonantHarmonics() {
        val result = antennaAnalyser.countAntinodes(
            resourceLoader.loadResourceLines("/test_input_1.txt"),
            true
        )

        assertThat(result).isEqualTo(34)
    }

}