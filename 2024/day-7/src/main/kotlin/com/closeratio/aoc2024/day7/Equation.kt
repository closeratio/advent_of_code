package com.closeratio.aoc2024.day7

import com.closeratio.aoc2024.day7.Operator.PLUS

class Equation(
    val parts: List<Pair<Operator, Long>>
) {

    fun compute(): Long = parts
        .fold(0L) { acc, (operator, value) ->
            when (operator) {
                PLUS -> acc + value
                Operator.MULTIPLY -> acc * value
            }
        }

}