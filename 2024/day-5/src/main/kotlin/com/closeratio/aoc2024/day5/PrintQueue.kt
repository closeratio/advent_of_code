package com.closeratio.aoc2024.day5

data class PrintQueue(
    val pages: List<Int>
) {

    fun isValid(rules: List<OrderRule>) = this == sort(rules)

    fun sort(rules: List<OrderRule>): PrintQueue {
        val sortedPages = pages.sortedWith { left, right ->
            val potentialRules = setOf(
                OrderRule(left, right),
                OrderRule(right, left)
            )

            val rule = rules.first { it in potentialRules }
            if (rule.first == left) -1 else 1
        }

        return PrintQueue(sortedPages)
    }

    fun middlePage(): Int = pages[pages.size / 2]

}