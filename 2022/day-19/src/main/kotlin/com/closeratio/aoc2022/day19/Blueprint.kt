package com.closeratio.aoc2022.day19

import com.closeratio.aoc2022.day19.robot.ClayRobot
import com.closeratio.aoc2022.day19.robot.GeodeRobot
import com.closeratio.aoc2022.day19.robot.ObsidianRobot
import com.closeratio.aoc2022.day19.robot.OreRobot

class Blueprint(
    val id: Int,
    val oreRobot: OreRobot,
    val clayRobot: ClayRobot,
    val obsidianRobot: ObsidianRobot,
    val geodeRobot: GeodeRobot
)

