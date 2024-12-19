package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.math.Vec2

class Side(
    val positions: MutableList<Vec2>
) {
    fun last() = positions.last()

    operator fun plusAssign(curr: Vec2) {
        positions.add(curr)
    }

    override fun toString(): String {
        return "Side(positions=$positions)"
    }

}