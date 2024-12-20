package com.closeratio.aoc2024.day13

import com.closeratio.aoc.common.math.Vec2
import java.lang.System.lineSeparator

data class ClawMachine(
    val aButton: Vec2,
    val bButton: Vec2,
    val prize: Vec2
) {
    companion object {
        fun from(text: String): ClawMachine = text
            .split(lineSeparator())
            .let { (firstLine, secondLine, thirdLine) ->
                ClawMachine(
                    parseVec(firstLine),
                    parseVec(secondLine),
                    parseVec(thirdLine)
                )
            }

        private fun parseVec(text: String): Vec2 = text
            .split(": ")
            .last()
            .split(", ")
            .map { offset -> offset.drop(2).toLong() }
            .let { (x, y) -> Vec2(x, y) }
    }

    fun solve(): Long {
        val determinant = (aButton.x * bButton.y) - (aButton.y * bButton.x)
        val a = (prize.x * bButton.y - prize.y * bButton.x) / determinant
        val b = (aButton.x * prize.y - aButton.y * prize.x) / determinant

        val xSolutionExists = aButton.x * a + bButton.x * b == prize.x
        val ySolutionExists = aButton.y * a + bButton.y * b == prize.y

        return if (xSolutionExists && ySolutionExists) {
            a * 3 + b
        } else 0
    }

}
