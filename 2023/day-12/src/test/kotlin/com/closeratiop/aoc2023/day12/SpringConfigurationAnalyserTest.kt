package com.closeratiop.aoc2023.day12

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2023.day12.SpringConfigurationAnalyser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SpringConfigurationAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var sprintConfigurationAnalyser: SpringConfigurationAnalyser

    @Test
    fun sumPossibleArrangements_returnsExpectedValue() {
        val result = sprintConfigurationAnalyser.sumPossibleArrangements(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(21)
    }

}
