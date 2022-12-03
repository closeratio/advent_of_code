package com.closeratio.aoc2022.day3

data class Compartment(
    val items: List<Item>
) {

    private val itemSet: Set<Item> = items.toSet()
    fun commonItems(other: Compartment): Set<Item> = itemSet.intersect(other.itemSet)

    companion object {
        fun from(chars: String): Compartment = Compartment(
            chars.map(Item.Companion::from)
        )

    }
}