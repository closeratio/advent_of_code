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

    fun nextStates(
        maxMinutes: Long,
        blueprint: Blueprint
    ): List<Inventory> {
        if (minute > maxMinutes) {
            return emptyList()
        }

        return listOfNotNull(
            computeState(blueprint.clayRobot),
            computeState(blueprint.oreRobot),
            computeState(blueprint.obsidianRobot),
            computeState(blueprint.geodeRobot)
        ).filter { it.minute < maxMinutes }
    }

    private fun computeState(
        robot: Robot
    ): Inventory? {
        val requiredMinutes = robot.requiredMinutesToBuild(this) ?: return null

        val newMinutes = minute + requiredMinutes

        return copy(
            minute = newMinutes,
            oreCount = oreCount + (oreRobots * requiredMinutes),
            clayCount = clayCount + (clayRobots * requiredMinutes),
            obsidianCount = obsidianCount + (obsidianRobots * requiredMinutes),
            geodeCount = geodeCount + (geodeRobots * requiredMinutes)
        ).let(robot::build)

    }

    fun calculateGeodeCountAtEnd(
        maxMinutes: Long
    ): Long = geodeCount + (maxMinutes - minute) * geodeRobots

    fun theoreticalGeodeCount(
        maxMinutes: Long
    ): Long {
        if (minute == maxMinutes) {
            return geodeCount
        }

        return geodeCount + ((minute + 1)..maxMinutes)
            .map { it - minute }
            .sumOf { geodeRobots + it }
    }

}
