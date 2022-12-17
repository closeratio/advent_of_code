package com.closeratio.aoc2022.day14

import com.closeratio.aoc.common.math.Vec2

class SandSimulation(
    private val rocks: Set<Vec2>
) {

    private val maxY = rocks.maxBy(Vec2::y).y

    private fun printString(sand: Set<Vec2>) {
        val minX = (sand + rocks).minBy(Vec2::x).x
        val maxX = (sand + rocks).maxBy(Vec2::x).x
        val minY = (sand + rocks).minBy(Vec2::y).y
        val maxY = (sand + rocks).maxBy(Vec2::y).y

        val visString = (minY..maxY).joinToString("\n") { y ->
            (minX..maxX).joinToString("") { x ->
                when (Vec2(x, y)) {
                    in rocks -> "#"
                    in sand -> "o"
                    else -> " "
                }
            }
        }

        println(visString)
    }

    fun determineValidDirection(
        currSand: Vec2,
        restingSand: Set<Vec2>,
        includeFloor: Boolean,
        floorY: Long
    ): Vec2? {
        val down = currSand.down()
        return listOf(
            down,
            down.left(),
            down.right()
        ).firstOrNull {
            val noSandOrRock = it !in restingSand && it !in rocks
            if (includeFloor) {
                noSandOrRock && it.y < floorY
            } else {
                noSandOrRock
            }
        }
    }

    fun simulateUntilFinished(
        includeFloor: Boolean = false
    ): Int {
        val restingSand = mutableSetOf<Vec2>()
        var fallingIntoAbyss = false
        var sandBlocked = false
        val floorY = maxY + 2

        while (!fallingIntoAbyss && !sandBlocked) {
            var currSand = Vec2(500, 0)
            var resting = false
            while (!resting && !fallingIntoAbyss) {
                // If this grain's level with the lowest rock, everything below is the abyss
                if (currSand.y >= maxY && !includeFloor) {
                    fallingIntoAbyss = true
                    continue
                }

                // Check 3 directions
                val validDirection = determineValidDirection(currSand, restingSand, includeFloor, floorY)

                if (validDirection != null) {
                    currSand = validDirection
                    continue
                }

                // If no directions are valid, then the sand must have come to rest on a flat surface
                resting = true
                restingSand += currSand

                // If the sand has come to rest at the source, then it is blocked
                if (currSand.y == 0L) {
                    sandBlocked = true
                }
            }
        }

        printString(restingSand)

        return restingSand.size
    }

}
