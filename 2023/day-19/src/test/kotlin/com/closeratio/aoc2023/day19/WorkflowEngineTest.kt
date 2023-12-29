package com.closeratio.aoc2023.day19

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class WorkflowEngineTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var workflowEngine: WorkflowEngine

    @Test
    fun sumAcceptedParts_returnsExpectedValue() {
        val result = workflowEngine.sumAcceptedParts(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(19114)
    }

    @Test
    fun sumTotalCombinations_returnsExpectedValue() {
        val result = workflowEngine.sumTotalCombinations(
            resourceLoader.loadResourceText("/test_input.txt")
        )

//        assertThat(result).isEqualTo(167_409_079_868_000)
    }

}
