package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2

data class SearchState(
    val pos: Vec2,
    val direction: Direction,
    val remainingSteps: Long
)
