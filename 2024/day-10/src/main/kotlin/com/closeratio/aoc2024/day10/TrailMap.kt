package com.closeratio.aoc2024.day10

import com.closeratio.aoc.common.math.Vec2

data class TrailMap(
    val map: Map<Vec2, Int>
) {

    fun findHeads(): Set<Vec2> = map
        .filter { (_, v) -> v == 0 }
        .map(Map.Entry<Vec2, Int>::key)
        .toSet()

    operator fun get(pos: Vec2): Int = map.getOrDefault(pos, -1)

}