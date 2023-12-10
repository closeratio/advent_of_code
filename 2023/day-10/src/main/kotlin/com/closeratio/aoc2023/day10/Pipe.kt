package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day10.PipeType.*

data class Pipe(
    val position: Vec2,
    val type: PipeType
) {

    fun possibleConnectedPositions(): Pair<Vec2, Vec2> = when (type) {
        HORIZONTAL -> position.left() to position.right()
        VERTICAL -> position.up() to position.down()
        L_BEND -> position.up() to position.right()
        J_BEND -> position.up() to position.left()
        SEVEN_BEND -> position.down() to position.left()
        F_BEND -> position.down() to position.right()
    }

}