package com.closeratio.aoc.common.math

import kotlin.math.abs

data class Vec2(
    val x: Long,
    val y: Long
) {
    companion object {
        val ZERO: Vec2 = Vec2(0, 0)
    }

    fun left(): Vec2 = Vec2(x - 1, y)
    fun right(): Vec2 = Vec2(x + 1, y)
    fun up(): Vec2 = Vec2(x, y - 1)
    fun down(): Vec2 = Vec2(x, y + 1)

    fun manhattanDistance(other: Vec2) = abs(x - other.x) + abs(y - other.y)

    fun immediatelyAdjacent(): Set<Vec2> = setOf(
        up(),
        down(),
        left(),
        right()
    )

    fun diagonals(): Set<Vec2> = setOf(
        up().left(),
        up().right(),
        down().left(),
        down().right()
    )

    fun isAdjacent(
        other: Vec2,
        includeDiagonals: Boolean = false
    ): Boolean {
        val adjacentVecs: Set<Vec2> = immediatelyAdjacent() + (if (includeDiagonals) diagonals() else emptySet())

        return other in adjacentVecs
    }

}
