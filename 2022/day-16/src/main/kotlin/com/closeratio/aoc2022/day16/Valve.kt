package com.closeratio.aoc2022.day16

import com.closeratio.aoc2022.day16.Valve.State.CLOSED
import com.closeratio.aoc2022.day16.Valve.State.OPEN

data class Valve(
    val id: String,
    val flowRate: Long,
    val state: State,
    val openMinute: Int?, // Only applicable if the valve is open,
    val connectedValveIds: Set<String>,
    val isCandidateValve: Boolean = true
) {

    enum class State {
        CLOSED,
        OPEN
    }

    fun hypotheticalPressureYield(
        currentMinute: Int,
        endMinute: Int
    ): Long = when (state) {
        // If the valve is closed, then it's a hypothetical yield which would have to start from the next minute.
        // Otherwise, the valve is open, and we can work out the real pressure yield up to the end minute.
        CLOSED -> (endMinute - (currentMinute + 1)) * flowRate
        else -> actualPressureYield(endMinute)
    }

    fun actualPressureYield(
        endMinute: Int
    ): Long = when (state) {
        CLOSED -> 0L
        else -> (endMinute - openMinute!!) * flowRate
    }

    fun open(minute: Int): Valve {
        if (state == OPEN) {
            throw IllegalStateException("Valve $id is already open at minute $minute. It was opened at minute $openMinute")
        }

        return copy(
            state = OPEN,
            openMinute = minute
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Valve

        if (id != other.id) return false
        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + state.hashCode()
        return result
    }

}
