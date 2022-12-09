package com.closeratio.aoc2022.day9

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MotionProcessorTest {

    @Autowired
    private lateinit var motionProcessor: MotionProcessor

    @Test
    fun computeTailPositionCount_givenTestInput1_returnsExpectedValue() {
        val result = motionProcessor.computeTailPositionCount("/test_input_1.txt")

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun computeTailPositionCountWith10Links_givenTestInput1_returnsExpectedValue() {
        val result = motionProcessor.computeTailPositionCount("/test_input_1.txt", 10)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun computeTailPositionCountWith10Links_givenTestInput2_returnsExpectedValue() {
        val result = motionProcessor.computeTailPositionCount("/test_input_2.txt", 10)

        assertThat(result).isEqualTo(36)
    }

}
