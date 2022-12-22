package com.closeratio.aoc2022.day17.rockshape

import com.closeratio.aoc.common.math.Vec2

class VerticalLine(
    rocks: Set<Vec2> = setOf(
        Vec2(2, 0),
        Vec2(2, -1),
        Vec2(2, -2),
        Vec2(2, -3)
    )
) : RockShape(rocks) {

    override val constructor: (Set<Vec2>) -> RockShape = {
        VerticalLine(it)
    }
}
