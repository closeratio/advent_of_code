package com.closeratio.aoc2022.day19

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2022.day19.robot.ClayRobot
import com.closeratio.aoc2022.day19.robot.GeodeRobot
import com.closeratio.aoc2022.day19.robot.ObsidianRobot
import com.closeratio.aoc2022.day19.robot.OreRobot
import org.springframework.stereotype.Component

@Component
class RobotSimulationParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseLine(line: String): Blueprint = line
        .split(": ")
        .let { (left, right) -> listOf(left) + right.split(". ") }
        .map { it.split(" ").mapNotNull(String::toLongOrNull) }
        .let { (idList, oreRobotList, clayRobotList, obsidianRobotList, geodeRobotList) ->
            Blueprint(
                idList.first().toInt(),
                OreRobot(oreRobotList.first()),
                ClayRobot(clayRobotList.first()),
                ObsidianRobot(obsidianRobotList[0], obsidianRobotList[1]),
                GeodeRobot(geodeRobotList[0], geodeRobotList[1])
            )
        }

    fun parse(path: String): RobotSimulation = resourceLoader
        .loadResourceLines(path)
        .map(::parseLine)
        .let(::RobotSimulation)

}
