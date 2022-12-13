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
            val result = items
                .asSequence()
                .mapIndexed { index, item ->
                    if (index < other.items.size) {
                        item.compareTo(other.items[index])
                    } else {
                        // If the other list is smaller and we've gotten to the end of it, we're out of order
                        1
                    }
                }
                .firstOrNull { it != 0 } ?: 0

            // If the items we've checked are all equal but this list is small than the other list, then we're in order
            if (result == 0 && items.size < other.items.size) {
                -1
            } else {
                result
            }
        }

        else -> compareTo(InnerListItem(mutableListOf(other)))
    }
}
