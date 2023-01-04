package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.math.Vec2

abstract class Tile(
    val position: Vec2
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(position=$position)"
    }

}

