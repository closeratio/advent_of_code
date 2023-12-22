package com.closeratio.aoc2023.day17

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day17.Direction.*
import kotlin.Long.Companion.MAX_VALUE

data class Path(
    val positions: Set<Vec2>,
    val heatLossTotal: Long
) {

    private val positionsList = positions.toList()

    fun calculateNextPaths(heatmap: Heatmap): List<Path> = positions
        .last()
        .immediatelyAdjacent()
        .filter { it !in positions }
        .map {
            Path(
                positions + it,
                heatLossTotal + heatmap.getOrDefault(it, MAX_VALUE)
            )
        }
        .filter { it.isValid(heatmap) }

    private fun isValid(
        heatmap: Heatmap
    ): Boolean {
        if (positionsList.last().x !in heatmap.xRange || positionsList.last().y !in heatmap.yRange) {
            return false
        }

        if (positions.size <= 3) {
            return true
        }

        return positionsList
            .takeLast(5)
            .windowed(2)
            .map { (first, second) -> direction(first, second) }
            .toSet()
            .size > 1
    }

    fun searchState(
        heatmap: Heatmap
    ): SearchState {
        val last = positionsList.last()

        val direction = if (positions.size == 1) RIGHT else direction(
            positionsList.takeLast(2).first(),
            last
        )

        var remainingSteps = 0L
        var currPath = nextInSameDirection(direction, heatmap)
        while (currPath?.isValid(heatmap) == true) {
            remainingSteps++
            currPath = currPath.nextInSameDirection(direction, heatmap)
        }

        return SearchState(
            last,
            direction,
            remainingSteps
        )
    }

    private fun direction(
        first: Vec2,
        second: Vec2
    ): Direction = when (second) {
        first.left() -> LEFT
        first.right() -> RIGHT
        first.up() -> UP
        first.down() -> DOWN
        else -> throw IllegalArgumentException("Unknown transition: $first -> $second")
    }

    private fun nextInSameDirection(
        direction: Direction,
        heatmap: Heatmap
    ): Path? {
        val next = when (direction) {
            UP -> positionsList.last().up()
            RIGHT -> positionsList.last().right()
            DOWN -> positionsList.last().down()
            LEFT -> positionsList.last().left()
        }

        if (next in positions) {
            return null
        }

        return Path(
            positions + next,
            heatLossTotal + heatmap.getOrDefault(positionsList.last(), MAX_VALUE)
        )
    }
}
