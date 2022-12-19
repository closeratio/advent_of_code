package com.closeratio.aoc2022.day16

import com.closeratio.aoc2022.day16.Valve.State.CLOSED
import com.closeratio.aoc2022.day16.Valve.State.OPEN

class WorldState(
    val valveMap: Map<String, Valve>,
    val currentPosition: String,
    val minuteCounter: Int
) {

    fun totalPressureYield(
        endMinute: Int
    ): Long = valveMap
        .values
        .sumOf { it.totalPressureYield(minuteCounter, endMinute) }

    fun nextStates(
        endMinute: Int
    ): List<WorldState> {
        if (isInEndState()) {
            return emptyList()
        }

        val currValve = valveMap.getValue(currentPosition)

        val currValveOpenedState = listOfNotNull(
            when {
                currValve.state == CLOSED && currValve.flowRate > 0 -> WorldState(
                    // Because the valveMap is keyed by ID, appending this new map to it will overwrite the "old" valve
                    valveMap + mapOf(currentPosition to currValve.open(minuteCounter)),
                    currentPosition,
                    minuteCounter + 1
                )

                else -> null
            }
        )

        val states = currValveOpenedState + currValve
            .connectedValveIds
            .map { id ->
                WorldState(
                    valveMap,
                    id,
                    minuteCounter + 1
                )
            }

        return states.filter { it.minuteCounter <= endMinute }
    }

    fun isInEndState(): Boolean = valveMap
        .values
        .filter { it.flowRate > 0 }
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
