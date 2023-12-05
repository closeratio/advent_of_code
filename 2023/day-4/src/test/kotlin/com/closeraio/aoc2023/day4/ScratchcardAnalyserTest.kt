package com.closeraio.aoc2023.day4

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2023.day4.ScratchcardAnalyser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ScratchcardAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var scratchcardAnalyser: ScratchcardAnalyser

    @Test
    fun sumScratchcardValues_returnsExpectedValue() {
        val result = scratchcardAnalyser.sumScratchcardValues(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun sumTotalScratchcards_returnsExpectedValue() {
        val result = scratchcardAnalyser.sumTotalScratchcards(
            resourceLoader.loadResourceLines("/test_input.txt")
        )

        assertThat(result).isEqualTo(30)
    }

}