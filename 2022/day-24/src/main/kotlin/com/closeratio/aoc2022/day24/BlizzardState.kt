package com.closeratio.aoc2022.day24

data class BlizzardState(
    val blizzards: List<Blizzard>,
    val minute: Int
) {

    val blizzardPositions = blizzards.map(Blizzard::position).toSet()

    fun nextState(
        walls: Walls
    ): BlizzardState {

        val movedBlizzards = blizzards.map { it.move(walls) }

        return BlizzardState(
            movedBlizzards,
            minute + 1
        )
    }

}

