package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory

class GeodeRobot(oreCost: Long, obsidianCost: Long) : Robot(oreCost, 0, obsidianCost) {

    override fun addRobot(inventory: Inventory): Inventory = inventory
        .copy(geodeRobots = inventory.geodeRobots + 1)

}
