package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day24.Blizzard.Direction.*

data class WorldState(
    val walls: Set<Vec2>,
    val blizzards: List<Blizzard>,
    val ownPosition: Vec2,
    val minute: Int
) {

    private val wallsMaxY = walls.maxOf(Vec2::y)

    fun inEndState(): Boolean = ownPosition.y == wallsMaxY

    fun nextStates(): List<WorldState> {
        if (inEndState()) return listOf(this)

        val movedBlizzards = blizzards.map { it.move(walls) }
        val newBlizzardPositions = movedBlizzards.map(Blizzard::position)
        val newPositionCandidates = (ownPosition.immediatelyAdjacent() + ownPosition)
            .filter { it !in newBlizzardPositions }
            .filter { it !in walls }
            .filter { it.y >= 0 }

        return newPositionCandidates.map {
            WorldState(
                walls,
                movedBlizzards,
                it,
                minute + 1
            )
        }
    }

    fun print() {
        val minY = 0
        val minX = 0
        val maxX = walls.maxOf(Vec2::x)

        val blizzardPositionMap = blizzards.groupBy(Blizzard::position)

        val string = (minY..wallsMaxY).joinToString("\n") { y ->
            (minX..maxX).joinToString("") { x ->
                val pos = Vec2(x, y)
                when (pos) {
                    in walls -> "#"
                    in blizzardPositionMap -> {
                        val blizzards = blizzardPositionMap.getValue(pos)
                        if (blizzards.size > 1) {
                            blizzards.size.toString()
                        } else {
                            when (blizzards.first().direction) {
                                UP -> "^"
                                DOWN -> "v"
                                LEFT -> "<"
                                RIGHT -> ">"
                            }
                        }
                    }

                    ownPosition -> "E"
                    else -> "."
                }
            }
        }

        println(string)
    }

}