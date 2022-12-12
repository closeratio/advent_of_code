package com.closeratio.aoc2022.day11

class Monkey(
    val id: Id,
    val items: MutableList<Item>,
    val divisor: Long,
    private val worryModifier: (Item) -> Item,
    private val throwFunction: (Item) -> Id
) {

    var inspectionCount = 0L

    data class Id(val value: Int)

    fun inspect(item: Item): Item {
        inspectionCount++
        return worryModifier(item)
    }

    fun throwTarget(item: Item): Id = throwFunction(item)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Monkey

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Monkey(id=$id, inspectionCount=$inspectionCount)"
    }


}
