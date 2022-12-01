package com.closeratio.aoc2022.day1

import com.closeratio.aoc2022.common.AocRunner
import org.springframework.stereotype.Component

@Component
class Day1Runner(
    private val calorieCounter: CalorieCounter
): AocRunner() {

    override fun getDay(): Int = 1

    override fun part1Function(): () -> Long = {
        calorieCounter.largestCalorieGroup("/input.txt").total()
    }

    override fun part2Function(): () -> Long = {
        calorieCounter.top3Groups("/input.txt")
            .map(CalorieGroup::total)
            .sum()
    }

}
