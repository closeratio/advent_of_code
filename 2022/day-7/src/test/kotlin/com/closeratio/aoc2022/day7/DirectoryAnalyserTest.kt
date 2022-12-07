package com.closeratio.aoc2022.day7

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class DirectoryAnalyserTest {

    @Autowired
    private lateinit var directoryAnalyser: DirectoryAnalyser

    @Test
    fun computeDirectoryStructure_givenInputData_returnsExpectedValue() {
        val result = directoryAnalyser.parseDirectoryStructure("/test_input.txt")

        assertThat(result.totalSize()).isEqualTo(48381165)
    }

    @Test
    fun sumDirectoriesWithMaxSize_givenInputData_returnsExpectedValue() {
        val result = directoryAnalyser.sumDirectoriesWithMaxSize("/test_input.txt", 100_000)

        assertThat(result).isEqualTo(95437)
    }

    @Test
    fun computeDirectorySizeToDelete_givenInputData_returnsExpectedValue() {
        val result = directoryAnalyser.computeDirectorySizeToDelete("/test_input.txt")

        assertThat(result).isEqualTo(24_933_642)
    }

}
