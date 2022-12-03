package com.closeratio.aoc2022.day3

import com.closeratio.aoc2022.common.CommonConfig
import com.closeratio.aoc2022.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [CommonConfig::class]
)
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


}