package com.closeratio.aoc2022.day4

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class AssignmentListTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun computeFullyOverlappingPairs_givenTestInput_computesExpectedResult() {
        val result = AssignmentList
            .from(resourceLoader.loadResourceLines("/test_input.txt"))
            .computeFullyOverlappingPairs()

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun computePartlyOverlappingPairs_givenTestInput_computesExpectedResult() {
        val result = AssignmentList
            .from(resourceLoader.loadResourceLines("/test_input.txt"))
            .computePartlyOverlappingPairs()

        assertThat(result).isEqualTo(4)
    }

}