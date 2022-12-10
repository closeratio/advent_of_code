package com.closeratio.aoc2022.day9

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day9Runner(
    private val motionProcessor: MotionProcessor
) : Aoc2022Runner() {

    override fun getDay(): Int = 9

    override fun part1Function(): () -> Int = {
        motionProcessor.computeTailPositionCount("/2022_day_9_input.txt")
    }

    override fun part2Function(): () -> Int = {
        motionProcessor.computeTailPositionCount("/2022_day_9_input.txt", 10)
    }

}
