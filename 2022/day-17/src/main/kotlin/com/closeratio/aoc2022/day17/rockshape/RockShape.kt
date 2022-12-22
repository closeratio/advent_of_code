package com.closeratio.aoc2022.day17.rockshape

import com.closeratio.aoc.common.math.Vec2

abstract class RockShape(
    val rocks: Set<Vec2>
) {

    abstract val constructor: (rocks: Set<Vec2>) -> RockShape

    fun down(): RockShape = constructor(rocks.map(Vec2::down).toSet())
    fun left(): RockShape = constructor(rocks.map(Vec2::left).toSet())
    fun right(): RockShape = constructor(rocks.map(Vec2::right).toSet())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RockShape

        if (rocks != other.rocks) return false

        return true
    }

    override fun hashCode(): Int {
        return rocks.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(rocks=$rocks)"
    }

    fun overlapsWith(otherRocks: Set<Vec2>): Boolean = rocks.intersect(otherRocks).isNotEmpty()

}

