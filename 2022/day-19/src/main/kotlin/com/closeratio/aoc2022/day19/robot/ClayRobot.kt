package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory

class ClayRobot(oreCost: Long) : Robot(oreCost, 0, 0) {

    override fun addRobot(inventory: Inventory): Inventory = inventory
        .copy(clayRobots = inventory.clayRobots + 1)

}
