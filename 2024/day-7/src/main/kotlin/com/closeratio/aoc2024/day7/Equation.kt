package com.closeratio.aoc2024.day7

import com.closeratio.aoc2024.day7.Operator.*

data class Equation(
    val elements: List<Element>
) {

    fun compute(): Long = elements
        .drop(1)
        .chunked(2)
        .fold((elements.first() as Value).value) { acc, (operator, value) ->
            value as Value
            when (operator as Operator) {
                PLUS -> acc + value.value
                MULTIPLY -> acc * value.value
                CONCATENATION -> ("$acc${value.value}".toLong())
            }
        }

}

