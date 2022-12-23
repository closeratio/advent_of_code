package com.closeratio.aoc2022.day17

class GameStateSimulation(
    private val initialState: GameState
) {

    fun simulateUntilTotalRocksFallen(count: Long): Long {
        var currState = initialState

        val previousStates = mutableMapOf<GameStateKey, GameState>()

        while (currState.fallenRocks < count) {
            currState = currState.iterate()

            val key = currState.generateKey()
            if (key in previousStates) {
                return computeFinalHeightUsingLoop(
                    currState,
                    previousStates,
                    count
                )
            }

            previousStates[key] = currState
        }

        return currState.height()
    }

    private fun computeFinalHeightUsingLoop(
        currState: GameState,
        previousStates: Map<GameStateKey, GameState>,
        count: Long
    ): Long {
        val prevState = previousStates.getValue(currState.generateKey())

        val prevHeight = prevState.height()
        val heightDelta = currState.height() - prevHeight
        val fallenRockDelta = currState.fallenRocks - prevState.fallenRocks
        val remainingRocksAtStartOfLoop = count - prevState.fallenRocks

        val loopCount = remainingRocksAtStartOfLoop / fallenRockDelta
        val extraRocksRequired = remainingRocksAtStartOfLoop % fallenRockDelta
        val extraHeight = previousStates
            .entries
            .first { it.value.fallenRocks == prevState.fallenRocks + extraRocksRequired.toInt() }
            .value
            .height() - prevHeight

        return prevHeight + (heightDelta * loopCount) + extraHeight
    }

}
