package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2

data class DigSite(
    val trenchTiles: Set<Vec2>
) {
    private val minX = trenchTiles.minOf(Vec2::x)
    private val maxX = trenchTiles.maxOf(Vec2::x)
    private val minY = trenchTiles.minOf(Vec2::y)
    private val maxY = trenchTiles.maxOf(Vec2::y)

    val xRange = minX..maxX
    val yRange = minY..maxY
}
