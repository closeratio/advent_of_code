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

        val listHistory = decrypter.mix()
        listOf(
            listOf(1, 2, -3, 3, -2, 0, 4),
            listOf(2, 1, -3, 3, -2, 0, 4),
            listOf(1, -3, 2, 3, -2, 0, 4),
            listOf(1, 2, 3, -2, -3, 0, 4),
            listOf(1, 2, -2, -3, 0, 3, 4),
            listOf(1, 2, -3, 0, 3, 4, -2),
            listOf(1, 2, -3, 0, 3, 4, -2),
            listOf(1, 2, -3, 4, 0, 3, -2)
        ).map { list -> list.map(::ListItem) }.forEachIndexed { index, list ->
            assertThat(listHistory[index]).isEqualTo(list)
        }

        val mixed = listHistory.last()
        val items = listOf(
            decrypter.getAt(mixed, 1000),
            decrypter.getAt(mixed, 2000),
            decrypter.getAt(mixed, 3000)
        )
        assertThat(items).isEqualTo(
            listOf(4, -3, 2).map(::ListItem)
        )

        val result = decrypter.mixAndSum()

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun parse_mixAndSumRealData_returnsExpectedValue() {
        val decrypter = listParser.parse("/2022_day_20_input.txt")
        val result = decrypter.mixAndSum()

        assertThat(result).isGreaterThan(2396)
    }

}
