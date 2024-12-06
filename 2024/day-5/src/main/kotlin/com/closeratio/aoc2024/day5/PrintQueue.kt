package com.closeratio.aoc2024.day5

data class PrintQueue(
    val pages: List<Int>
) {

    val pageIndexMap = pages
        .mapIndexed { index, value -> value to index }
        .toMap()

    fun isValid(rules: List<OrderRule>) = rules.all { rule ->
        val firstIndex = pageIndexMap[rule.first]
        val secondIndex = pageIndexMap[rule.second]

        if (firstIndex != null && secondIndex != null) {
            firstIndex < secondIndex
        } else {
            true
        }
    }

}