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
    fun parse_inOrder_returnsExpectedValues() {
        val result = packetPairParser.parse("/test_input.txt")
            .mapIndexedNotNull { index, pair ->
                if (pair.inOrder()) index + 1 else null
            }
            .sum()

        assertThat(result).isEqualTo(13)
    }

}
