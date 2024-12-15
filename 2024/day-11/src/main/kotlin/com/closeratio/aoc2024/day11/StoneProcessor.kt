package com.closeratio.aoc2024.day11

import org.springframework.stereotype.Service

@Service
class StoneProcessor {

    fun blink(
        input: String,
        times: Int
    ): Long {
        val initialSet = StoneSet.from(input)
        var currentSet = initialSet
        (0..<times).forEach { _ ->
            currentSet = currentSet.blink()
        }

        return currentSet.count
    }

}