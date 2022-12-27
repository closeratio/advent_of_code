package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory

class ObsidianRobot(oreCost: Long, clayCost: Long) : Robot(oreCost, clayCost, 0) {

    override fun addRobot(inventory: Inventory): Inventory = inventory
        .copy(obsidianRobots = inventory.obsidianRobots + 1)

}
