package com.closeratio.aoc2022.day16

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2022.day16.Valve.State.CLOSED
import org.springframework.stereotype.Component

@Component
class ValveStateSimulationParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseValveLine(line: String): Valve {
        val (valveString, tunnelString) = line.split("; ")
        val (_, id) = valveString.split(" ")
        val (_, flowRateString) = valveString.split("=")
        val connectedValveIds = tunnelString
            .split(" ")
            .drop(4)
            .map { it.trim(',') }
            .toSet()
        return Valve(
            id,
            flowRateString.toLong(),
            CLOSED,
            null,
            connectedValveIds
        )
    }

    fun parse(path: String): WorldStateSimulation = resourceLoader
        .loadResourceLines(path)
        .map(::parseValveLine)
        .let { valves ->
            WorldStateSimulation(
                WorldState(
                    valves.associateBy(Valve::id),
                    "AA",
                    1
                )
            )
        }

}

