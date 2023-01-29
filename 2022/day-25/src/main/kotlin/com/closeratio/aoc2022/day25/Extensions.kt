package com.closeratio.aoc2022.day25

import kotlin.math.pow

fun String.snafuToLong(): Long = reversed()
    .mapIndexed { index, char ->
        val multiplier = when (char) {
            '0' -> 0
            '1' -> 1
            '2' -> 2
            '-' -> -1
            '=' -> -2
            else -> throw IllegalArgumentException("Unexpected char: $char")
        }

        multiplier * 5.0.pow(index).toLong()
    }
    .sum()

fun Long.toSnafu(): String {
    val mapping = listOf(
        "0" to 0,
        "1" to -1,
        "2" to -2,
        "=" to 2,
        "-" to 1
    )

    val snafuChars = mutableListOf<String>()
    var curr = this
    while (curr != 0L) {
        val (char, offset) = mapping[(curr % 5).toInt()]
        snafuChars += char
        curr += offset
        curr /= 5
    }

    return snafuChars.reversed().joinToString("")
}