package com.closeratio.aoc2023.day3

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class SchematicAnalyserTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var schematicAnalyser: SchematicAnalyser

    @Test
    fun partNumberSum_returnsExpectedValue() {
        val result = schematicAnalyser.partNumberSum(resourceLoader.loadResourceLines("/test-input.txt"))

        assertThat(result).isEqualTo(4361)
    }

}