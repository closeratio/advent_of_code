package com.closeratio.aoc2022.day20

import java.util.*

class ListDecrypter(
    private val items: List<ListItem>
) {

    fun getAt(list: List<ListItem>, index: Int): ListItem =
        list[(list.indexOf(ListItem(0)) + index) % list.size]

    fun mix(): List<List<ListItem>> {
        val modifiedList = LinkedList(items)
        val listHistory = mutableListOf(
            items
        )
//        println(modifiedList)

        items.forEach { item ->

            val moveRight = item.value >= 0
            val oldIndex = modifiedList.indexOf(item)

            val newIndex = if (moveRight) {
                val wrapCount = (oldIndex + item.value) / items.size
                (oldIndex + item.value + wrapCount) % modifiedList.size
            } else {
                val wrapCount = (oldIndex + item.value) / items.size - 1
                val wraps = (oldIndex + item.value) <= 0
                val offset = (oldIndex + item.value + if (wraps) wrapCount else 0) % modifiedList.size
                (modifiedList.size + offset)
            }

            modifiedList.removeAt(oldIndex)
            modifiedList.add(newIndex, item)

            listHistory.add(LinkedList(modifiedList))

//            println(modifiedList)
        }

        return listHistory
    }

    fun mixAndSum(): Int = mix()
        .last()
        .let {
            listOf(
                getAt(it, 1000),
                getAt(it, 2000),
                getAt(it, 3000)
            )
        }.sumOf(ListItem::value)

}
