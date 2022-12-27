package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory

abstract class Robot(
    val oreCost: Long,
    val clayCost: Long,
    val obsidianCost: Long
) {

    abstract fun addRobot(inventory: Inventory): Inventory

    fun build(inventory: Inventory): Inventory = inventory
        .copy(
            oreCount = inventory.oreCount - oreCost,
            clayCount = inventory.clayCount - clayCost,
            obsidianCount = inventory.obsidianCount - obsidianCost
        )
        .let(::addRobot)

}
