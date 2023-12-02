package com.closeratio.aoc2023.day2

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class GameAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var gameAnalyser: GameAnalyser

    @Test
    fun computePossibleGames_returnsExpectedValue() {
        val result = gameAnalyser.computePossibleGames(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(8)
    }

}
