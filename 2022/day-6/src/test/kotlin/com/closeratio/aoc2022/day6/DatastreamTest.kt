package com.closeratio.aoc2022.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DatastreamTest {

    companion object {
        @JvmStatic
        fun datastreams(): List<Arguments> = listOf(
            Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7, 19),
            Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz", 5, 23),
            Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 6, 23),
            Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10, 29),
            Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11, 26)
        )
    }

    @ParameterizedTest
    @MethodSource("datastreams")
    fun computePacketIndex_givenDatastream_computesExpectedValue(
        datastreamInput: String,
        expectedIndex: Int
    ) {
        val stream = Datastream(datastreamInput)
        assertThat(stream.computePacketIndex()).isEqualTo(expectedIndex)
    }

    @ParameterizedTest
    @MethodSource("datastreams")
    fun computeMessageIndex_givenDatastream_computesExpectedValue(
        datastreamInput: String,
        unused: Int,
        expectedIndex: Int
    ) {
        val stream = Datastream(datastreamInput)
        assertThat(stream.computeMessageIndex()).isEqualTo(expectedIndex)
    }

}
