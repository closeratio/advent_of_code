package com.closeratio.aoc2022.day20

import java.util.*

class ListDecrypter(
    private val items: List<ListItem>
) {

    fun getAt(list: List<ListItem>, index: Int): ListItem =
        list[(list.indexOf(list.find { it.value == 0L }) + index) % list.size]

    fun mix(
        times: Int = 1
    ): List<ListItem> {
        val modifiedList = LinkedList(items)

        repeat(times) {
            items.forEach { item ->
                val oldIndex = modifiedList.indexOf(item)
                modifiedList.removeAt(oldIndex)

                val newIndex = (oldIndex + item.value).mod(modifiedList.size)
                modifiedList.add(newIndex, item)
            }
        }

        return modifiedList
    }

    fun mixAndSum(
        times: Int = 1
    ): Long = mix(times)
        .let {
            listOf(
                getAt(it, 1000),
                getAt(it, 2000),
                getAt(it, 3000)
            )
        }.sumOf(ListItem::value)

}
