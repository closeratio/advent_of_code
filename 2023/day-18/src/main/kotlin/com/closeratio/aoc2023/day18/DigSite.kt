package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2

data class DigSite(
    val trenchTiles: Set<Vec2>
) {
    val minX = trenchTiles.minOf(Vec2::x)
    val maxX = trenchTiles.maxOf(Vec2::x)
    val minY = trenchTiles.minOf(Vec2::y)
    val maxY = trenchTiles.maxOf(Vec2::y)

    val xRange = minX..maxX
    val yRange = minY..maxY
}
