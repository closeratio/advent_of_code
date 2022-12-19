package com.closeratio.aoc2022.day16

import com.closeratio.aoc2022.day16.Valve.State.CLOSED
import com.closeratio.aoc2022.day16.Valve.State.OPEN

class WorldState(
    val valveMap: Map<String, Valve>,
    val currentPosition: String,
    val minuteCounter: Int,
    val endMinute: Int
) {

    val hypotheticalPressureYield = valveMap
        .values
        .sumOf { it.hypotheticalPressureYield(minuteCounter, endMinute) }

    val actualPressureYield = valveMap
        .values
        .sumOf { it.actualPressureYield(endMinute) }

    private fun availableDestinations(): Set<Valve> = valveMap
        .values
        .filter { it.id != currentPosition }
        .filter(Valve::isCandidateValve)
        .filter { it.flowRate > 0 && it.state == CLOSED }
        .toSet()

    fun nextStates(routeMap: RouteMap): List<WorldState> {
        val destinations = availableDestinations()

        if (isInEndState()) {
            return emptyList()
        }

        return destinations
            .map { valve ->
                val routeLength = routeMap.routeLength(currentPosition, valve.id)

                WorldState(
                    valveMap + mapOf(valve.id to valve.open(minuteCounter + routeLength)),
                    valve.id,
                    minuteCounter + routeLength + 1,
                    endMinute
                )
            }
    }

    private fun isInEndState(): Boolean = valveMap
        .values
        .filter { it.flowRate > 0 }
        .filter(Valve::isCandidateValve)
        .all { it.state == OPEN }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorldState

        if (valveMap != other.valveMap) return false
        if (currentPosition != other.currentPosition) return false
        if (minuteCounter != other.minuteCounter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = valveMap.hashCode()
        result = 31 * result + currentPosition.hashCode()
        result = 31 * result + minuteCounter
        return result
    }

    override fun toString(): String {
        return "WorldState(valveMap=$valveMap, currentPosition='$currentPosition', minuteCounter=$minuteCounter)"
    }

}
