package com.closeratio.aoc2022.day22

import com.closeratio.aoc2022.day22.TurnAction.Direction
import com.closeratio.aoc2022.day22.TurnAction.Direction.LEFT
import com.closeratio.aoc2022.day22.TurnAction.Direction.RIGHT

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun turn(direction: Direction): Orientation = when (direction) {
        RIGHT -> {
            when (this) {
                NORTH -> EAST
                WEST -> NORTH
                SOUTH -> WEST
                EAST -> SOUTH
            }
        }

        LEFT -> {
            when (this) {
                NORTH -> WEST
                WEST -> SOUTH
                SOUTH -> EAST
                EAST -> NORTH
            }
        }
    }

}
