package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class MonkeyMapParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseMap(lines: List<String>): Map<Vec2, Tile> = TODO()

    fun parseActions(line: String): List<Action> = TODO()

    fun parse(path: String): MonkeyMap = resourceLoader
        .loadResourceLines(path)
        .let { lines ->
            MonkeyMap(
                parseMap(lines.dropLast(2)),
                parseActions(lines.last())
            )
        }


}

