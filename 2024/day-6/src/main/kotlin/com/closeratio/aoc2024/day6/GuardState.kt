package com.closeratio.aoc2024.day6

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day6.Direction.*

class GuardState(
    val position: Vec2,
    val direction: Direction
) {

    fun nextState(
        obstructions: Set<Vec2>
    ): GuardState {

        val potentialNextPosition = when (direction) {
            UP -> position.up()
            DOWN -> position.down()
            LEFT -> position.left()
            RIGHT -> position.right()
        }

        return if (potentialNextPosition !in obstructions) {
            return GuardState(
                potentialNextPosition,
                direction
            )
        } else {
            val newDirection = when (direction) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }

            GuardState(
                position,
                newDirection
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GuardState

        if (position != other.position) return false
        if (direction != other.direction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + direction.hashCode()
        return result
    }


}

