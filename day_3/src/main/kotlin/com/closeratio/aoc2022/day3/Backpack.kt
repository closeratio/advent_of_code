package com.closeratio.aoc2022.day3

data class Backpack(
    val firstCompartment: Compartment,
    val secondCompartment: Compartment
) {

    val itemSet = (firstCompartment.items + secondCompartment.items).toSet()
    fun prioritySum(): Long {
        val common = firstCompartment.commonItems(secondCompartment)
        return common.sumOf(Item::priority)
    }

    fun commonItem(first: Backpack, second: Backpack): Item = itemSet
        .intersect(first.itemSet)
        .intersect(second.itemSet)
        .first()

    companion object {
        fun from(line: String): Backpack = Backpack(
            line.take(line.length / 2).let(Compartment.Companion::from),
            line.takeLast(line.length / 2).let(Compartment.Companion::from)
        )
    }
}

