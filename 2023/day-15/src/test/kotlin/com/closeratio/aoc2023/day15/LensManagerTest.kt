package com.closeratio.aoc2023.day15

import com.closeratio.aoc.common.AocTest
import com.closeratio.aoc.common.ResourceLoader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class LensManagerTest {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Autowired
    private lateinit var lensManager: LensManager

    @Test
    fun computeFocusingPower_returnsExpectedValue() {
        val result = lensManager.computeFocusingPower(
            resourceLoader.loadResourceText("/test_input.txt")
        )

        assertThat(result).isEqualTo(145)
    }

}
