package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day24.Blizzard.Direction.*

data class Blizzard(
    val position: Vec2,
    val direction: Direction
) {

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    fun move(walls: Walls): Blizzard {
        val proposedNextPosition = when (direction) {
            UP -> position.up()
            DOWN -> position.down()
            LEFT -> position.left()
            RIGHT -> position.right()
        }

        if (!walls.overlaps(proposedNextPosition)) {
            return Blizzard(proposedNextPosition, direction)
        }

        return Blizzard(
            when (direction) {
                UP -> Vec2(position.x, walls.maxY - 1)
                DOWN -> Vec2(position.x, 1)
                LEFT -> Vec2(walls.maxX - 1, position.y)
                RIGHT -> Vec2(1, position.y)
            },
            direction
        )
    }

}