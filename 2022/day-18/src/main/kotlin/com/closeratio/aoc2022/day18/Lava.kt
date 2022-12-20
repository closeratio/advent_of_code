package com.closeratio.aoc2022.day18

import com.closeratio.aoc.common.math.Vec3

data class Lava(
    val lavaCubes: Set<Vec3>
) {

    // May contain duplicates
    private fun adjacentCubes(): List<Vec3> = lavaCubes
        .flatMap(Vec3::immediatelyAdjacent)
        .filter { it !in lavaCubes }

    fun computeSurfaceArea(): Int = adjacentCubes().size

    fun computeExteriorSurfaceArea(): Int {
        val outsideCubes = computeOutsideCubes()
        return adjacentCubes()
            .filter { it in outsideCubes }
            .size
    }

    private fun computeOutsideCubes(): Set<Vec3> {
        val minX = lavaCubes.minBy(Vec3::x).x - 1
        val maxX = lavaCubes.maxBy(Vec3::x).x + 1
        val minY = lavaCubes.minBy(Vec3::y).y - 1
        val maxY = lavaCubes.maxBy(Vec3::y).y + 1
        val minZ = lavaCubes.minBy(Vec3::z).z - 1
        val maxZ = lavaCubes.maxBy(Vec3::z).z + 1

        val start = Vec3(minX, minY, minZ)
        val outsideCubes = mutableSetOf<Vec3>()

        val candidates = mutableSetOf(start)

        while (candidates.isNotEmpty()) {
            val curr = candidates.first()
            candidates.remove(curr)
            outsideCubes += curr

            candidates += curr.immediatelyAdjacent()
                .filter { it !in candidates }
                .filter { it !in outsideCubes }
                .filter { it !in lavaCubes }
                .filter { (x, y, z) ->
                    x in minX..maxX && y in minY..maxY && z in minZ..maxZ
                }
        }

        return outsideCubes
    }

}

