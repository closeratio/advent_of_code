package com.closeratio.aoc.common.math

import kotlin.math.abs
import kotlin.math.absoluteValue

data class Vec2(
    val x: Long,
    val y: Long
) {

    constructor(x: Int, y: Int) : this(
        x.toLong(),
        y.toLong()
    )

    constructor(x: String, y: String) : this(
        x.toLong(),
        y.toLong()
    )

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

    fun allAdjacent(): Set<Vec2> = immediatelyAdjacent() + diagonals()

    fun isAdjacent(
        other: Vec2,
        includeDiagonals: Boolean = false
    ): Boolean {
        val adjacentVecs: Set<Vec2> = immediatelyAdjacent() + (if (includeDiagonals) diagonals() else emptySet())

        return other in adjacentVecs
    }

    fun lineTo(other: Vec2): Set<Vec2> {
        val xDiff = other.x - x
        val yDiff = other.y - y

        require(xDiff.absoluteValue != 0L && yDiff.absoluteValue != 0L) {
            "Line must be horizontal or vertical"
        }

        return if (xDiff == 0L) {
            (if (yDiff > 0) y..other.y else other.y..y).map { currY -> Vec2(x, currY) }.toSet()
        } else {
            (if (xDiff > 0) x..other.x else other.x..x).map { currX -> Vec2(currX, y) }.toSet()
        }
    }

    fun scale(value: Long): Vec2 = Vec2(x * value, y * value)

    operator fun times(value: Long) = scale(value)

}
