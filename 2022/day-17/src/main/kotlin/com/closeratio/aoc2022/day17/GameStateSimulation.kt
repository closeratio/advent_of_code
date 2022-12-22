package com.closeratio.aoc2022.day17

import com.closeratio.aoc.common.math.Vec2
import kotlin.math.absoluteValue

class GameStateSimulation(
    private val initialState: GameState
) {

    fun simulateUntilTotalRocksFallen(count: Int): Int {
        var currState = initialState
//        currState.printString()

        while (currState.fallenRocks < count) {
            currState = currState.iterate()
//            currState.printString()
        }

        return currState
            .rocks
            .minOf(Vec2::y)
            .toInt()
            .absoluteValue + 1
    }

}
