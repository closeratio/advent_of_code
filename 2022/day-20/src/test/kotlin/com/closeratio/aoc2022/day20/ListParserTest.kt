package com.closeratio.aoc2022.day20

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ListParserTest {

    @Autowired
    private lateinit var listParser: ListParser

    @Test
    fun parse_mixAndSum_returnsExpectedValue() {
        val decrypter = listParser.parse("/test_input.txt")

        val mixed = decrypter.mix()
        val items = listOf(
            decrypter.getAt(mixed, 1000).value,
            decrypter.getAt(mixed, 2000).value,
            decrypter.getAt(mixed, 3000).value
        )
        assertThat(items).isEqualTo(listOf(4L, -3L, 2L))

        val result = decrypter.mixAndSum()

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun parse_mixAndSumRealData_returnsExpectedValue() {
        val decrypter = listParser.parse("/2022_day_20_input.txt")
        val result = decrypter.mixAndSum()

        assertThat(result).isEqualTo(7153)
    }

    @Test
    fun parse_mixAndSumWithDecryptionKey_returnsExpectedValue() {
        val decrypter = listParser.parse("/test_input.txt", 811_589_153L)
        val result = decrypter.mixAndSum(10)

        assertThat(result).isEqualTo(1_623_178_306L)
    }

}
