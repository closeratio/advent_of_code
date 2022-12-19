package com.closeratio.aoc2022.day16

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer


@AocTest
class ValveStateSimulationParserTest {

    @TestConfiguration
    class SpringAsyncConfig : AsyncConfigurer {
        @Bean
        fun taskExecutor(): AsyncTaskExecutor = SimpleAsyncTaskExecutor()
    }

    @Autowired
    private lateinit var parser: ValveStateSimulationParser

    @Test
    fun parse_computeMaxPossiblePressureSolo_returnsExpectedValue() {
        val sim = parser.parse("/test_input.txt")
        val result = sim.computeMaxPossiblePressureSolo()

        assertThat(result).isEqualTo(1651)
    }

    @Test
    fun parse_computeMaxPossiblePressureSoloRealData_returnsExpectedValue() {
        val sim = parser.parse("/2022_day_16_input.txt")
        val result = sim.computeMaxPossiblePressureSolo()

        assertThat(result).isEqualTo(1617)
    }

    @Test
    fun parse_computeMaxPossiblePressureWithElephantFriend_returnsExpectedValue() {
        val sim = parser.parse("/test_input.txt")
        val result = sim.computeMaxPossiblePressureWithElephantFriend()

        assertThat(result).isEqualTo(1707)
    }

}
