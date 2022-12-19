package com.closeratio.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.combinations
import org.springframework.core.task.AsyncTaskExecutor
import java.util.*
import java.util.concurrent.Future

class WorldStateSimulation(
    private val initialState: WorldState,
    private val executor: AsyncTaskExecutor
) {

    private val routeMap = RouteMap.from(initialState.valveMap)

    fun computeMaxPossiblePressureSolo(
        startingState: WorldState = initialState
    ): Long {
        val comparator = Comparator
            .comparing(WorldState::hypotheticalPressureYield)
            .reversed()

        val stateQueue = PriorityQueue(comparator)
        stateQueue.add(startingState)

        val bestStateCombinationMap = mutableMapOf<WorldState, Long>()

        while (stateQueue.isNotEmpty()) {
            val currState = stateQueue.remove()

            if (currState.hypotheticalPressureYield > (bestStateCombinationMap[currState] ?: -1)) {
                bestStateCombinationMap[currState] = currState.hypotheticalPressureYield
            } else {
                continue
            }

            if (currState.minuteCounter < currState.endMinute) {
                stateQueue.addAll(currState.nextStates(routeMap).filter { it.minuteCounter <= currState.endMinute })
            }
        }

        val bestState = bestStateCombinationMap
            .keys
            .maxBy { it.actualPressureYield }

        return bestState.actualPressureYield
    }

    fun computeMaxPossiblePressureWithElephantFriend(): Long {
        val candidateValveIds = initialState
            .valveMap
            .values
            .filter { it.flowRate > 0 }
            .map(Valve::id)
            .toSet()

        val combinations = (1..(candidateValveIds.size / 2))
            .flatMap { size -> candidateValveIds.combinations(size).map(List<String>::toSet) }

        val results = combinations
            .map { humanValves ->
                executor.submit<Long> {
                    val elephantValves = candidateValveIds - humanValves

                    val humanInitialState = generateStateWithCandidates(humanValves)
                    val elephantInitialState = generateStateWithCandidates(elephantValves)

                    val humanResult = computeMaxPossiblePressureSolo(humanInitialState)
                    val elephantResult = computeMaxPossiblePressureSolo(elephantInitialState)

                    humanResult + elephantResult
                }
            }
            .map(Future<Long>::get)

        return results.max()
    }

    private fun generateStateWithCandidates(ids: Set<String>): WorldState = WorldState(
        initialState.valveMap + initialState.valveMap
            .filterKeys { it !in ids }
            .filterValues { it.flowRate > 0 }
            .mapValues { (_, valve) -> valve.copy(isCandidateValve = false) },
        initialState.currentPosition,
        initialState.minuteCounter,
        26
    )

}
