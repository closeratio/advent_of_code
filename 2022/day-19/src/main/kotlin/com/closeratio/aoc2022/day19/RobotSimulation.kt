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
        val stateQueue = LinkedList(
            listOf(
                Inventory(0, 0, 0, 0, 0, 1, 0, 0, 0)
            )
        )

        while (stateQueue.isNotEmpty()) {
            val state = stateQueue.first()
            stateQueue.remove(state)
            stateQueue.addAll(state.nextStates(maxMinutes, blueprint))

            maxGeodes = maxOf(maxGeodes, state.geodeCount)
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

