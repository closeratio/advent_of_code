package com.closeratio.aoc2023.day19

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class WorkflowEnginerParser {

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

}
