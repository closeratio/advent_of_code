package com.closeratio.aoc2024.day11

import org.springframework.stereotype.Service

@Service
class StoneProcessor {

    fun blink(
        input: String,
        times: Int
    ): Long = StoneSet
        .from(input)
        .blink(
            times
        )

}