package com.closeratio.aoc2022.day23

class WorldStateSimulation(
    private val initialState: WorldState
) {

    fun computeEmptyGroundTiles(rounds: Int): Long {
        var currState = initialState
        repeat(rounds) {
            currState = currState.nextState()
        }

        return currState.computeEmptyGroundTiles()
    }

    fun computeRoundsUntilFinished(): Long {
        var prevState = initialState
        var currState = prevState.nextState()
        var roundCount = 1L

        while (currState.elfMap != prevState.elfMap) {
            prevState = currState
            currState = currState.nextState()
            roundCount++
        }

        return roundCount
    }

}