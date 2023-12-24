package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day18.Direction.*
import org.springframework.stereotype.Component

@Component
class DigSiteParser {

    private fun parseInstruction(
        line: String,
        useHexInstructions: Boolean
    ): Instruction = line
        .split(" ")
        .let { (directionString, amountString, hexString) ->
            val (direction, amount) = if (useHexInstructions) {
                val hexTrimmed = hexString.trim('(', '#', ')')
                val direction = hexTrimmed.last().let {
                    when (it) {
                        '0' -> RIGHT
                        '1' -> DOWN
                        '2' -> LEFT
                        '3' -> UP
                        else -> throw IllegalArgumentException("Unknown direction: $it")
                    }
                }
                val amount = hexTrimmed.take(5).toLong(16)
                direction to amount
            } else {
                when (directionString) {
                    "U" -> UP
                    "D" -> DOWN
                    "L" -> LEFT
                    "R" -> RIGHT
                    else -> throw IllegalArgumentException("Unknown direction: $directionString")
                } to amountString.toLong()
            }
            Instruction(direction, amount)
        }

    fun parseDigSite(
        lines: List<String>,
        useHexInstructions: Boolean
    ): DigSite {
        var currPos = Vec2.ZERO
        val trenchTiles = mutableSetOf(currPos)
        lines.map { parseInstruction(it, useHexInstructions) }
            .forEach { instruction ->
                (1..instruction.amount).forEach {
                    currPos = when (instruction.direction) {
                        UP -> currPos.up()
                        DOWN -> currPos.down()
                        LEFT -> currPos.left()
                        RIGHT -> currPos.right()
                    }
                    trenchTiles += currPos
                }
            }

        return DigSite(trenchTiles)
    }

}