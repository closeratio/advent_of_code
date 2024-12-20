package com.closeratio.aoc2024.day13

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ClawMachineProcessorTest {

    @Autowired
    private lateinit var clawMachineProcessor: ClawMachineProcessor

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun solve() {
        val result = clawMachineProcessor.solve(
            resourceLoader.loadResourceText("/test_input_1.txt")
        )

        assertThat(result).isEqualTo(480)
    }

}