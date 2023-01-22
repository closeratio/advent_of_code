package com.closeratio.aoc2022.day19

import org.slf4j.LoggerFactory
import java.util.*

class RobotSimulation(
    private val blueprints: List<Blueprint>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private fun computeQualityLevel(
        maxMinutes: Long,
        blueprint: Blueprint
    ): Long {
        var maxGeodes = 0L
        val stateQueue = PriorityQueue(Comparator.comparingLong(Inventory::geodeRobots).reversed())
        stateQueue += Inventory(0, 0, 0, 0, 0, 1, 0, 0, 0)

        var earliestGeodeRobotMinute = Long.MAX_VALUE

        while (stateQueue.isNotEmpty()) {
            val state = stateQueue.poll()
            if (state.geodeRobots == 0L && state.minute >= earliestGeodeRobotMinute) {
                continue
            }

            stateQueue.addAll(state.nextStates(maxMinutes, blueprint))
            if (state.geodeRobots > 0) {
                earliestGeodeRobotMinute = minOf(earliestGeodeRobotMinute, state.minute)
            }

            if (state.geodeCount > 0) {
                maxGeodes = maxOf(maxGeodes, state.calculateGeodeCountAtEnd(maxMinutes))
            }
        }

        return maxGeodes * blueprint.id
    }

    fun computeQualityLevelSum(
        maxMinutes: Long
    ): Long = blueprints.sumOf {
        log.info("Computing quality level of blueprint ${it.id}")
        val result = computeQualityLevel(maxMinutes, it)
        log.info("Quality level of blueprint ${it.id} = $result")
        result
    }

}

