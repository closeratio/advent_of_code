package com.closeratio.aoc2022.day3

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class BackpackTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun prioritySum_givenTestInput_computesExpectedValue() {
        val result = resourceLoader
            .loadResourceLines("/test_input.txt")
            .map(Backpack.Companion::from)
            .sumOf(Backpack::prioritySum)

        assertThat(result).isEqualTo(157)
    }

    @Test
    fun commonItem_givenTestInput_computesExpectedValue() {
        val result = resourceLoader
            .loadResourceLines("/test_input.txt")
            .chunked(3)
            .map { (first, second, third) ->
                Backpack.from(first).commonItem(Backpack.from(second), Backpack.from(third))
            }.sumOf(Item::priority)

        assertThat(result).isEqualTo(70)
    }


}
