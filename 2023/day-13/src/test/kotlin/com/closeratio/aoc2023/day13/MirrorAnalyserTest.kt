package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class MirrorAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var mirrorAnalyser: MirrorAnalyser

    @Test
    fun summarise_returnsExpectedValue() {
        val result = mirrorAnalyser.summarise(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(405)
    }

}
