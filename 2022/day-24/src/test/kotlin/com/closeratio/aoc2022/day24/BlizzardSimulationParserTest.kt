package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class BlizzardSimulationParserTest {

    @Autowired
    private lateinit var blizzardSimulationParser: BlizzardSimulationParser

    @Test
    fun parse_calculateStepsToGoal_returnsExpectedValue() {
        val result = blizzardSimulationParser
            .parse("/test_input.txt")
            .calculateMinutesToGoal()

        assertThat(result.minute).isEqualTo(18)
    }

    @Test
    fun parse_calculateStepsToGoalRealData_returnsExpectedValue() {
        val result = blizzardSimulationParser
            .parse("/2022_day_24_input.txt")
            .calculateMinutesToGoal()

        assertThat(result.minute).isEqualTo(247)
    }

    @Test
    fun parse_calculateMinutesToGoalTwice_returnsExpectedValue() {
        val result = blizzardSimulationParser
            .parse("/test_input.txt")
            .calculateMinutesToGoalTwice()

        assertThat(result.minute).isEqualTo(54)
    }

}