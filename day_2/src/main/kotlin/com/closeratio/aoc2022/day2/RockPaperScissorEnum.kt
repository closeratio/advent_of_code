package com.closeratio.aoc2022.day2

import com.closeratio.aoc2022.day2.GameResult.*

enum class RockPaperScissorEnum(
    private val points: Long,
    private val computeResult: (other: RockPaperScissorEnum) -> GameResult,
    private val computeRequiredPlay: (result: GameResult) -> RockPaperScissorEnum
) {

    ROCK(1, {
        when (it) {
            ROCK -> DRAW
            PAPER -> LOSS
            SCISSORS -> WIN
        }
    }, {
        when (it) {
            DRAW -> ROCK
            WIN -> PAPER
            LOSS -> SCISSORS
        }
    }),
    PAPER(2, {
        when (it) {
            ROCK -> WIN
            PAPER -> DRAW
            SCISSORS -> LOSS
        }
    }, {
        when (it) {
            DRAW -> PAPER
            WIN -> SCISSORS
            LOSS -> ROCK
        }
    }),
    SCISSORS(3, {
        when (it) {
            ROCK -> LOSS
            PAPER -> WIN
            SCISSORS -> DRAW
        }
    }, {
        when (it) {
            DRAW -> SCISSORS
            WIN -> ROCK
            LOSS -> PAPER
        }
    });

    fun computePoints(other: RockPaperScissorEnum): Long = points + computeResult(other).points

    fun computePoints(result: GameResult): Long = result.points + computeRequiredPlay(result).points

    companion object {
        fun decodeChar(char: Char): RockPaperScissorEnum = when (char) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSORS
            else -> throw IllegalArgumentException("Unable to decode character $char")
        }

        fun decodePlayPair(line: String): Pair<RockPaperScissorEnum, RockPaperScissorEnum> =
            decodeChar(line.first()) to decodeChar(line.last())
    }

}

