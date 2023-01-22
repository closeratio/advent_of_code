package com.closeratio.aoc2022.day24

import com.closeratio.aoc.common.math.Vec2
import java.util.*

class BlizzardSimulation(
    private val walls: Walls,
    initialBlizzardState: BlizzardState,
    private val initialState: PathState = PathState(Vec2(1, 0), 0),
    private val goal: Vec2 = Vec2(walls.maxX - 1, walls.maxY)
) {

    private val blizzardStateCache = mutableMapOf(initialState.minute to initialBlizzardState)

    fun calculateMinutesToGoal(): PathState {
        val openStates = PriorityQueue(Comparator.comparingInt(PathState::minute))
        val closedStates = mutableSetOf<PathState>()

        openStates.add(initialState)

        while (openStates.isNotEmpty()) {
            val currState = openStates.poll()
            if (currState in closedStates) {
                continue
            }

            closedStates += currState

            if (currState.position == goal) {
                return currState
            } else {
                val blizzardState = blizzardStateCache.getOrPut(currState.minute + 1) {
                    blizzardStateCache.getValue(currState.minute).nextState(walls)
                }

                openStates += currState.nextStates(walls, blizzardState)
                    .filter { it !in closedStates }
            }
        }

        throw IllegalStateException("Unable to find route to exit")
    }

    fun calculateMinutesToGoalTwice(): PathState {
        val firstLegEnd = calculateMinutesToGoal()

        val secondSimulation = BlizzardSimulation(
            walls,
            blizzardStateCache.getValue(firstLegEnd.minute),
            firstLegEnd,
            Vec2(1, 0)
        )
        val secondLegEnd = secondSimulation.calculateMinutesToGoal()

        return BlizzardSimulation(
            walls,
            secondSimulation.blizzardStateCache.getValue(secondLegEnd.minute),
            secondLegEnd,
            goal
        ).calculateMinutesToGoal()
    }

}

