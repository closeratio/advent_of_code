package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2

data class Walls(
    val tiles: Set<Vec2>
) {
    val maxX = tiles.maxOf(Vec2::x)
    val maxY = tiles.maxOf(Vec2::y)

    fun overlaps(position: Vec2): Boolean {
        if (position.x == 0L || position.x == maxX) {
            return true
        }

        if (position.y == 0L && position.x != 1L) {
            return true
        }

        if (position.y == maxY && position.x != (maxX - 1)) {
            return true
        }

        return false
    }

}