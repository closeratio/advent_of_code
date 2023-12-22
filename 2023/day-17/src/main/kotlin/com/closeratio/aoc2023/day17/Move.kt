package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2

data class Move(
    val pos: Vec2,
    val direction: Direction,
    val directionCount: Long
)
