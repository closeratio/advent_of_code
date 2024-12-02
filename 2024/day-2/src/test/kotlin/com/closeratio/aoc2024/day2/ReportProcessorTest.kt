package com.closeratio.aoc2024.day2

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class ReportProcessorTest {

    @Autowired
    private lateinit var reportProcessor: ReportProcessor

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun coundSafeReports() {
        val result = reportProcessor.countSafeReports(resourceLoader.loadResourceLines("/test_input.txt"))

        assertThat(result).isEqualTo(2)
    }

}