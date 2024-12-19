package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.math.Vec2

data class Plot(
    val pos: Vec2,
    val type: String
) {

    fun calculateRequiredFence(
        plots: Set<Vec2>
    ): Long = pos
        .immediatelyAdjacent()
        .filter { it !in plots }
        .size
        .toLong()

}
