package com.closeratio.aoc2023.day2

import com.closeratio.aoc2023.day2.Colour.*
import org.springframework.stereotype.Component

@Component
class GameAnalyser {

    private fun parseGames(
        lines: List<String>
    ): List<Game> = lines.map(::parseGame)

    private fun parseHandfull(segment: String): Handfull = segment
        .split(",")
        .map(String::trim)
        .map { it.split(" ") }
        .map { (countString, colourString) ->
            val count = countString.toLong()
            val colour = when (colourString) {
                "red" -> RED
                "green" -> GREEN
                "blue" -> BLUE
                else -> throw IllegalArgumentException("Unhandled colour: $colourString")
            }

            colour to count
        }
        .toMap()
        .let {
            Handfull(
                it[RED] ?: 0,
                it[GREEN] ?: 0,
                it[BLUE] ?: 0
            )
        }

    private fun parseGame(line: String): Game {
        val id = """^Game (\d+)""".toRegex().find(line)!!.groupValues[1].toLong()
        val handfulls = line
            .split(":")[1]
            .split(";")
            .map { parseHandfull(it) }

        return Game(id, handfulls)
    }

    private fun isGamePossible(game: Game): Boolean =
        game.maxRed() <= 12 && game.maxGreen() <= 13 && game.maxBlue() <= 14

    fun computePossibleGames(
        lines: List<String>
    ): Long = parseGames(lines)
        .filter(::isGamePossible)
        .map(Game::id)
        .sum()

    fun sumPowerSets(
        lines: List<String>
    ): Long = parseGames(lines)
        .map(Game::powerSet)
        .sum()

}
