package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2

data class PathState(
    val position: Vec2,
    val minute: Int
) {
    fun nextStates(
        walls: Walls,
        blizzardState: BlizzardState
    ): List<PathState> {
        val newPositionCandidates = (position.immediatelyAdjacent() + position)
            .filter { it !in blizzardState.blizzardPositions }
            .filterNot(walls::overlaps)
            .filter { it.y >= 0 }

        return newPositionCandidates.map {
            PathState(
                it,
                minute + 1
            )
        }
    }

}

