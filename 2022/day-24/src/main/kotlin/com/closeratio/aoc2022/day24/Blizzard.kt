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

    fun move(walls: Set<Vec2>): Blizzard {
        val proposedNextPosition = when (direction) {
            UP -> position.up()
            DOWN -> position.down()
            LEFT -> position.left()
            RIGHT -> position.right()
        }

        if (proposedNextPosition !in walls) {
            return Blizzard(proposedNextPosition, direction)
        }

        return Blizzard(
            when (direction) {
                UP -> Vec2(position.x, walls.maxOf(Vec2::y) - 1)
                DOWN -> Vec2(position.x, 1)
                LEFT -> Vec2(walls.maxOf(Vec2::x) - 1, position.y)
                RIGHT -> Vec2(1, position.y)
            },
            direction
        )
    }

}