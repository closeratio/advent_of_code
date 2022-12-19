package com.closeratio.aoc2022.day16

import java.util.*

class WorldStateSimulation(
    private val initialState: WorldState
) {

    private val routeMap = RouteMap.from(initialState.valveMap)

    fun computeMaxPossiblePressure(endMinute: Int): Long {
        val comparator = Comparator
            .comparing(WorldState::hypotheticalPressureYield)
            .reversed()

        val stateQueue = PriorityQueue(comparator)
        stateQueue.add(initialState)

        val bestStateCombinationMap = mutableMapOf<WorldState, Long>()

        while (stateQueue.isNotEmpty()) {
            val currState = stateQueue.remove()

            if (currState.hypotheticalPressureYield > (bestStateCombinationMap[currState] ?: -1)) {
                bestStateCombinationMap[currState] = currState.hypotheticalPressureYield
            } else {
                continue
            }

            if (currState.minuteCounter < endMinute) {
                stateQueue.addAll(currState.nextStates(routeMap).filter { it.minuteCounter <= endMinute })
            }
        }

        val bestState = bestStateCombinationMap
            .keys
            .maxBy { it.actualPressureYield }

        return bestState.actualPressureYield
    }

}
