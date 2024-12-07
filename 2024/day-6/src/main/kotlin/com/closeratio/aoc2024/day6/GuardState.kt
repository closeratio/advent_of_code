package com.closeratio.aoc2024.day6

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day6.Direction.*

class GuardState(
    val position: Vec2,
    val direction: Direction,
    val previousState: GuardState?
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
                direction,
                this
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
                newDirection,
                this
            )
        }
    }

}

