package com.closeratio.aoc2022.day19.robot

import com.closeratio.aoc2022.day19.Inventory
import kotlin.math.ceil

abstract class Robot(
    private val oreCost: Long,
    private val clayCost: Long,
    private val obsidianCost: Long
) {

    abstract fun addRobot(inventory: Inventory): Inventory

    fun build(inventory: Inventory): Inventory = inventory
        .copy(
            oreCount = inventory.oreCount - oreCost,
            clayCount = inventory.clayCount - clayCost,
            obsidianCount = inventory.obsidianCount - obsidianCost
        )
        .let(::addRobot)

    fun requiredMinutesToBuild(inventory: Inventory): Long? {
        val requiredResourcesBeingMined = listOf(
            !(oreCost > 0 && inventory.oreRobots == 0L),
            !(clayCost > 0 && inventory.clayRobots == 0L),
            !(obsidianCost > 0 && inventory.obsidianRobots == 0L)
        ).all { it }

        if (!requiredResourcesBeingMined) {
            return null
        }

        return listOf(
            requiredMinutesForSpecificResource(inventory.oreCount, oreCost, inventory.oreRobots),
            requiredMinutesForSpecificResource(inventory.clayCount, clayCost, inventory.clayRobots),
            requiredMinutesForSpecificResource(inventory.obsidianCount, obsidianCost, inventory.obsidianRobots)
        ).max() + 1
    }

    private fun requiredMinutesForSpecificResource(
        currentAmount: Long,
        cost: Long,
        robotCount: Long
    ): Long {
        if (cost == 0L) {
            return 0
        } else if (cost <= currentAmount) {
            return 0
        }

        val amountToMine = cost - currentAmount
        return ceil(amountToMine / robotCount.toDouble()).toLong()
    }

}
