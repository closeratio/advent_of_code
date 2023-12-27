package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
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
    ): DigSite = lines.map { parseInstruction(it, useHexInstructions) }
        .fold(listOf(ZERO)) { acc, curr ->
            val (dir, amount) = curr
            val last = acc.last()
            acc + (last + when (dir) {
                UP -> Vec2(0, -amount)
                DOWN -> Vec2(0, amount)
                LEFT -> Vec2(-amount, 0)
                RIGHT -> Vec2(amount, 0)
            })
        }
        .let(::DigSite)

}
