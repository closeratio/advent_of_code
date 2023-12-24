package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day18.Direction.*
import org.springframework.stereotype.Component

@Component
class DigSiteParser {

    private fun parseInstruction(
        line: String
    ): Instruction = line
        .split(" ")
        .let { (directionString, amountString) ->
            Instruction(
                when (directionString) {
                    "U" -> UP
                    "D" -> DOWN
                    "L" -> LEFT
                    "R" -> RIGHT
                    else -> throw IllegalArgumentException("Unknown direction: $directionString")
                },
                amountString.toLong()
            )
        }

    fun parseDigSite(
        lines: List<String>
    ): DigSite {
        var currPos = Vec2.ZERO
        val trenchTiles = mutableSetOf(currPos)
        lines.map(::parseInstruction)
            .forEach { instruction ->
                (1..instruction.amount).forEach {
                    when (instruction.direction) {
                        UP -> currPos = currPos.up()
                        DOWN -> currPos = currPos.down()
                        LEFT -> currPos = currPos.left()
                        RIGHT -> currPos = currPos.right()
                    }
                    trenchTiles += currPos
                }
            }

        return DigSite(trenchTiles)
    }

}