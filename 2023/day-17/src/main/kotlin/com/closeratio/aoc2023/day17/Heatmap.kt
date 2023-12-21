package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2

class Heatmap(
    private val map: Map<Vec2, Long>
) {
    val minX = map.keys.minOf(Vec2::x)
    val maxX = map.keys.maxOf(Vec2::x)
    val minY = map.keys.minOf(Vec2::y)
    val maxY = map.keys.maxOf(Vec2::y)

    fun getValue(pos: Vec2): Long = map.getValue(pos)

}
