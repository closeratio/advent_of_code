package com.closeratio.aoc2022.day13

import java.util.*

class InnerListItem(
    val items: MutableList<Item> = LinkedList()
) : Item() {

    override fun toString(): String {
        return "$items"
    }

    override fun toOriginalString(): String = items
        .joinToString(",", "[", "]", transform = Item::toOriginalString)

    override fun compareTo(other: Item): Int = when (other) {
        is InnerListItem -> {
            items
                .asSequence()
                .mapIndexed { index, item ->
                    if (index < other.items.size) {
                        item.compareTo(other.items[index])
                    } else {
                        1
                    }
                }
                .firstOrNull { it != 0 } ?: 0
        }

        else -> compareTo(InnerListItem(mutableListOf(other)))
    }
}
