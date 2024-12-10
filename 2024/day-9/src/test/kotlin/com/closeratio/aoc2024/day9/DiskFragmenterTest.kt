package com.closeratio.aoc2024.day9

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class DiskFragmenterTest {

    @Autowired
    private lateinit var diskFragmenter: DiskFragmenter

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun fragmentAndCalculateChecksum() {
        val result = diskFragmenter.fragmentAndCalculateChecksum(
            resourceLoader.loadResourceText("/test_input_1.txt"),
        )

        assertThat(result).isEqualTo(1928)
    }

}