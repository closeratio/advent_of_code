package com.closeratio.aoc2022.day16

import com.closeratio.aoc2022.day16.Valve.State.CLOSED
import com.closeratio.aoc2022.day16.Valve.State.OPEN

data class Valve(
    val id: String,
    val flowRate: Long,
    val state: State,
    val openMinute: Int?, // Only applicable if the valve is open,
    val connectedValveIds: Set<String>
) {

    fun totalPressureYield(
        currentMinute: Int,
        endMinute: Int
    ): Long = when (state) {
        // If the valve is closed, then it's a hypothetical yield which would have to start from the next minute.
        // Otherwise, the valve is open, and we can work out the real pressure yield up to the end minute.
        CLOSED -> (endMinute - (currentMinute + 1)) * flowRate
        else -> (endMinute - openMinute!!) * flowRate
    }

    fun open(minute: Int): Valve {
        if (state == OPEN) {
            throw IllegalStateException("Valve $id is already open at minute $minute. It was opened at  minute $openMinute")
        }

        return copy(
            state = OPEN,
            openMinute = minute
        )
    }

    enum class State {
        CLOSED,
        OPEN
    }

}
