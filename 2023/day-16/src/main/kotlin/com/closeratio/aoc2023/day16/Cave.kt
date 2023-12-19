package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2

data class Cave(
    val minX: Long,
    val minY: Long,
    val maxX: Long,
    val maxY: Long,
    val mirrors: Map<Vec2, Mirror>
)
