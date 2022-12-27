package com.closeratio.aoc2022.day19

class RobotSimulation(
    private val blueprints: List<Blueprint>
) {

    private fun computeQualityLevel(
        maxMinutes: Long,
        blueprint: Blueprint
    ): Long {
        val exploredStates = mutableSetOf<Inventory>()
        val stateQueue = mutableSetOf(
            Inventory(0, 0, 0, 0, 0, 1, 0, 0, 0)
        )

        while (stateQueue.isNotEmpty()) {
            val state = stateQueue.first()
            stateQueue.remove(state)
            exploredStates += state
            stateQueue.addAll(state.nextStates(maxMinutes, blueprint)
                .filter { it !in exploredStates }
                .filter { it !in stateQueue }
            )
        }

        return exploredStates
            .filter { it.minute == maxMinutes }
            .maxOf(Inventory::geodeCount)
            .let { blueprint.id * it }
    }

    fun computeQualityLevelSum(
        maxMinutes: Long
    ): Long = blueprints.sumOf { computeQualityLevel(maxMinutes, it) }

}

