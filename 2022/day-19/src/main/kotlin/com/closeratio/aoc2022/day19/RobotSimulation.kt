package com.closeratio.aoc2022.day19

class RobotSimulation(
    private val blueprints: List<Blueprint>
) {

    fun computeQualityLavel(blueprint: Blueprint): Long = 0L

    fun computeQualityLevelSum(): Long = blueprints.sumOf(::computeQualityLavel)

}

