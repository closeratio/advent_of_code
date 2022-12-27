package com.closeratio.aoc2022.day19

import com.closeratio.aoc2022.day19.robot.Robot

data class Inventory(
    val minute: Long,

    val oreCount: Long,
    val clayCount: Long,
    val obsidianCount: Long,
    val geodeCount: Long,

    val oreRobots: Long,
    val clayRobots: Long,
    val obsidianRobots: Long,
    val geodeRobots: Long
) {

    private fun canAffordAll(blueprint: Blueprint): Boolean = canAfford(blueprint.oreRobot)
            && canAfford(blueprint.clayRobot)
            && canAfford(blueprint.obsidianRobot)
            && canAfford(blueprint.geodeRobot)

    private fun canAfford(robot: Robot): Boolean = oreCount >= robot.oreCost
            && clayCount >= robot.clayCost
            && obsidianCount >= robot.obsidianCost

    fun nextStates(
        maxMinutes: Long,
        blueprint: Blueprint
    ): List<Inventory> {
        if (minute >= maxMinutes) {
            return emptyList()
        }

        val intermediateState = copy(
            minute = minute + 1,
            oreCount = oreCount + oreRobots,
            clayCount = clayCount + clayRobots,
            obsidianCount = obsidianCount + obsidianRobots,
            geodeCount = geodeCount + geodeRobots
        )

        return listOfNotNull(
            if (!canAffordAll(blueprint)) intermediateState else null,
            if (canAfford(blueprint.oreRobot)) blueprint.oreRobot.build(intermediateState) else null,
            if (canAfford(blueprint.clayRobot)) blueprint.clayRobot.build(intermediateState) else null,
            if (canAfford(blueprint.obsidianRobot)) blueprint.obsidianRobot.build(intermediateState) else null,
            if (canAfford(blueprint.geodeRobot)) blueprint.geodeRobot.build(intermediateState) else null
        )
    }

}
