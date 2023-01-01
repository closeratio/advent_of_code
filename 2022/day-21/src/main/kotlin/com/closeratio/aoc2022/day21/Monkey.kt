package com.closeratio.aoc2022.day21

abstract class Monkey(
    val id: Id
) {

    data class Id(
        val value: String
    )

    abstract fun computeNumber(monkeyMap: Map<Id, Monkey>): Long
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
        return "${javaClass.simpleName}(id=$id)"
    }


}

