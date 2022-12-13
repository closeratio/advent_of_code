package com.closeratio.aoc2022.day13

class IntegerItem(
    val value: Int
) : Item() {

    override fun toString(): String {
        return "$value"
    }

    override fun toOriginalString(): String = "$value"

    override fun compareTo(other: Item): Int = when (other) {
        is IntegerItem -> value.compareTo(other.value)
        else -> InnerListItem(mutableListOf(this)).compareTo(other)
    }
}
