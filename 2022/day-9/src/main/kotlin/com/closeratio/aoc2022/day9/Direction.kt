package com.closeratio.aoc2022.day9

import com.closeratio.aoc.common.math.Vec2

enum class Direction(
    val vectorTransform: (Vec2) -> Vec2
) {
    UP({
        Vec2(it.x, it.y - 1)
    }),
    DOWN({
        Vec2(it.x, it.y + 1)
    }),
    LEFT({
        Vec2(it.x - 1, it.y)
    }),
    RIGHT({
        Vec2(it.x + 1, it.y)
    })
}
