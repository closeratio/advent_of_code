package com.closeratio.aoc2022.day17.rockshape

import com.closeratio.aoc.common.math.Vec2

class HorizontalLine(
    rocks: Set<Vec2> = setOf(
        Vec2(2, 0),
        Vec2(3, 0),
        Vec2(4, 0),
        Vec2(5, 0)
    )
) : RockShape(rocks) {

    override val constructor: (Set<Vec2>) -> RockShape = {
        HorizontalLine(it)
    }
}
