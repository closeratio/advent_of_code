package com.closeratio.aoc2022.day18

import com.closeratio.aoc.common.math.Vec3

data class Lava(
    val blocks: Set<Vec3>
) {

    fun computeSurfaceArea(): Int = blocks
        .sumOf { (it.immediatelyAdjacent() - blocks).size }

}

