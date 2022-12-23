package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory

abstract class Robot(
    val oreCost: Long,
    val clayCost: Long,
    val obsidianCost: Long
) {

    fun canAfford(inventory: Inventory): Boolean = inventory.oreCount >= oreCost
            && inventory.clayCount >= clayCost
            && inventory.obsidianCount >= obsidianCost

}
