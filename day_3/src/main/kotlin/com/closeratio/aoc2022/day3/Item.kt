package com.closeratio.aoc2022.day3

data class Item(
    val code: Char,
    val priority: Long
) {

    companion object {

        private val priorityMap = (('a'..'z') + ('A'..'Z'))
            .mapIndexed { index, char -> char to index + 1 }
            .toMap()
        fun from(it: Char): Item = Item(
            it,
            priorityMap.getValue(it).toLong()
        )

    }
}