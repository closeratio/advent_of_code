package com.closeratio.aoc2022.day13

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class PacketPairParserTest {

    @Autowired
    private lateinit var packetPairParser: PacketPairParser

    @Test
    fun sumCorrectOrderPairs_givenTestInput_returnsExpectedValues() {
        val result = packetPairParser.sumCorrectOrderPairs("/test_input.txt")

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun computeDecoderKey_givenTestInput_returnsExpectedValues() {
        val result = packetPairParser.computeDecoderKey("/test_input.txt")

        assertThat(result).isEqualTo(140)
    }

}
