package com.closeratio.aoc2022.day12

import com.closeratio.aoc.common.math.Vec2

data class Tile(
    val position: Vec2,
    val heightString: String
) {

    private companion object {
        val heightMapping = ('a'..'z')
            .mapIndexed { index, char -> char.toString() to index }
            .toMap() + mapOf(
            "S" to 0,
            "E" to 25
        )
    }

    val isStart = heightString == "S"
    val isEnd = heightString == "E"

    private val height = heightMapping.getValue(heightString)

    fun isTraversable(other: Tile): Boolean = (other.height - height) <= 1

}
