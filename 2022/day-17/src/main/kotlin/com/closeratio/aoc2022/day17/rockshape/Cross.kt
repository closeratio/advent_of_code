package com.closeratio.aoc2022.day17.rockshape

import com.closeratio.aoc.common.math.Vec2

class Cross(
    rocks: Set<Vec2> = setOf(
        Vec2(2, -1),
        Vec2(3, -1),
        Vec2(4, -1),
        Vec2(3, -2),
        Vec2(3, 0)
    )
) : RockShape(rocks) {

    override val constructor: (Set<Vec2>) -> RockShape = {
        Cross(it)
    }
}
