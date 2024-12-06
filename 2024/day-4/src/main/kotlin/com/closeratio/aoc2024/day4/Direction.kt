package com.closeratio.aoc2024.day4

import com.closeratio.aoc.common.math.Vec2

enum class Direction(
    val apply: (Vec2) -> Vec2
) {
    UP({ it.up() }),
    DOWN({ it.down() }),
    LEFT({ it.left() }),
    RIGHT({ it.right() }),
    UP_RIGHT({ it.up().right() }),
    DOWN_RIGHT({ it.down().right() }),
    DOWN_LEFT({ it.down().left() }),
    UP_LEFT({ it.up().left() }),
}