package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2
import java.util.*

class BlizzardSimulation(
    private val initialState: WorldState
) {

    private val goal = Vec2(
        initialState.walls.maxOf(Vec2::x) - 1,
        initialState.walls.maxOf(Vec2::y)
    )

    fun calculateMinutesToGoal(): Int {
        val openStates = PriorityQueue(Comparator.comparingInt(WorldState::minute))
        openStates.add(initialState)

        while (openStates.isNotEmpty()) {
            val currState = openStates.poll()

            if (currState.inEndState()) {
                println(currState.print())
                return currState.minute
            }

            openStates += currState.nextStates()
        }

        throw IllegalStateException("Unable to find route to exit")
    }

}

