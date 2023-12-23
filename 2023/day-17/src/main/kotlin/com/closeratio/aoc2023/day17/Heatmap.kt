package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2

class Heatmap(
    private val map: Map<Vec2, Long>
) {
    val minX = map.keys.minOf(Vec2::x)
    val maxX = map.keys.maxOf(Vec2::x)
    val minY = map.keys.minOf(Vec2::y)
    val maxY = map.keys.maxOf(Vec2::y)

    val xRange = minX..maxX
    val yRange = minY..maxY

    fun getValue(pos: Vec2): Long = map.getValue(pos)
    fun getOrDefault(pos: Vec2, default: Long) = map.getOrDefault(pos, default)

    operator fun contains(pos: Vec2): Boolean = pos.x in xRange && pos.y in yRange

}
