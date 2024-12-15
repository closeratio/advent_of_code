package com.closeratio.aoc2024.day11

data class Stone(
    val value: Long
) {
    private val stringLength = value.toString().length

    fun blink(): List<Stone> = when {
        value == 0L -> listOf(1L)
        stringLength.mod(2) == 0 -> listOf(
            value.toString().dropLast(stringLength / 2).toLong(),
            value.toString().drop(stringLength / 2).toLong()
        )

        else -> listOf(value * 2024)
    }.map(::Stone)

}