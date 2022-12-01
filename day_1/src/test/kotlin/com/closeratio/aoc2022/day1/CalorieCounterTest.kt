package com.closeratio.aoc2022.day1

import com.closeratio.aoc2022.common.CommonConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [CommonConfig::class]
)
class CalorieCounterTest {

    @Autowired
    private lateinit var calorieCounter: CalorieCounter

    @Test
    fun largestCalorieGroup_givenTestInput_computesCorrectOutput() {
        val result = calorieCounter.largestCalorieGroup("/test_input_1.txt")
        assertThat(result.total()).isEqualTo(24000)
    }

    @Test
    fun top3Groups_givenTestInput_computesCorrectOutput() {
        val result = calorieCounter.top3Groups("/test_input_1.txt")
        assertThat(result.map(CalorieGroup::total).sum()).isEqualTo(45000)
    }

}
