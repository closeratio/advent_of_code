package com.closeratio.aoc2022.day1

import com.closeratio.aoc2022.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day1Runner(
    private val calorieCounter: CalorieCounter
) : Aoc2022Runner() {

    override fun getDay(): Int = 1

    override fun part1Function(): () -> Long = {
        calorieCounter.largestCalorieGroup("/2022_day_1_input.txt").total()
    }

    override fun part2Function(): () -> Long = {
        calorieCounter.top3Groups("/2022_day_1_input.txt")
            .map(CalorieGroup::total)
            .sum()
    }

}
