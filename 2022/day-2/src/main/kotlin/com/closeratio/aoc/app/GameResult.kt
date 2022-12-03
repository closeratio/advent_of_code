package com.closeratio.aoc.app

enum class GameResult(
    val points: Long
) {
    WIN(6),
    LOSS(0),
    DRAW(3);

    companion object {
        fun decodeChar(char: Char): GameResult = when (char) {
            'X' -> LOSS
            'Y' -> DRAW
            'Z' -> WIN
            else -> throw IllegalArgumentException("Unable to decode character: $char")
        }
    }
}
