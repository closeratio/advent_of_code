package com.closeratio.aoc.common.math

import kotlin.math.abs

data class Vec3(
    val x: Long,
    val y: Long,
    val z: Long
) {
    companion object {
        val ZERO: Vec3 = Vec3(0, 0, 0)
    }

    fun left(): Vec3 = Vec3(x - 1, y, z)
    fun right(): Vec3 = Vec3(x + 1, y, z)
    fun up(): Vec3 = Vec3(x, y - 1, z)
    fun down(): Vec3 = Vec3(x, y + 1, z)
    fun forwards(): Vec3 = Vec3(x, y, z + 1)
    fun backwards(): Vec3 = Vec3(x, y, z - 1)

    fun manhattanDistance(other: Vec3) = abs(x - other.x) + abs(y - other.y) + abs(y - other.z)

    fun immediatelyAdjacent(): Set<Vec3> = setOf(
        up(),
        down(),
        left(),
        right(),
        forwards(),
        backwards()
    )

    fun isAdjacent(
        other: Vec3
    ): Boolean = manhattanDistance(other) == 1L

}
