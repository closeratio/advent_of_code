package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day22.TurnAction.Direction.LEFT
import com.closeratio.aoc2022.day22.TurnAction.Direction.RIGHT
import org.springframework.stereotype.Component

@Component
class MonkeyMapParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseMap(lines: List<String>): Map<Vec2, Tile> = lines
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char ->
                val pos = Vec2(x.toLong(), y.toLong())
                when (char) {
                    '.' -> EmptySpace(pos)
                    '#' -> Wall(pos)
                    else -> null
                }
            }.filterNotNull()
        }
        .associateBy(Tile::position)

    fun parseActions(line: String): List<Action> = line
        .fold(
            mutableListOf<String>()
        ) { acc, curr ->
            acc += if (acc.isEmpty()) {
                curr.toString()
            } else if (curr.toString().toLongOrNull() != null) {
                val last = acc.last()
                if (last.toLongOrNull() != null) {
                    acc.removeAt(acc.size - 1)
                    last + curr
                } else {
                    curr.toString()
                }
            } else {
                curr.toString()
            }

            acc
        }
        .map { chunk ->
            val amount = chunk.toLongOrNull()
            if (amount != null) {
                MoveAction(amount)
            } else {
                TurnAction(
                    when (chunk) {
                        "R" -> RIGHT
                        "L" -> LEFT
                        else -> throw IllegalArgumentException("Unhandled turn direction: $chunk")
                    }
                )
            }
        }

    fun parse(path: String): MonkeyMap = resourceLoader
        .loadResourceLines(path, false)
        .let { lines ->
            MonkeyMap(
                parseMap(lines.dropLast(3)),
                parseActions(lines.dropLast(1).last())
            )
        }


}

