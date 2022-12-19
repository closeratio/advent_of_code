package com.closeratio.aoc2022.day16

import java.util.*

class WorldStateSimulation(
    private val initialState: WorldState
) {

    fun computeMaxPossiblePressure(endMinute: Int): Long {
        val comparator = Comparator
            .comparing { ws: WorldState -> ws.totalPressureYield(endMinute) }
            .reversed()

        val stateQueue = PriorityQueue(comparator)
        stateQueue.add(initialState)

        val examinedStates = mutableSetOf<WorldState>()

        while (stateQueue.isNotEmpty() && !stateQueue.first().isInEndState()) {
            val currState = stateQueue.remove()
            examinedStates += currState

            stateQueue.addAll(currState.nextStates(endMinute).filter { it !in examinedStates })
        }

        if (stateQueue.isEmpty()) {
            throw IllegalStateException("Unable to compute strategy for releasing valves")
        }

        return stateQueue
            .first()
            .totalPressureYield(endMinute)
    }

}
