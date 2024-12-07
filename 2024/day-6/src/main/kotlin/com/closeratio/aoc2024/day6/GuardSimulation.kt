package com.closeratio.aoc2024.day6

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day6.Direction.UP
import org.springframework.stereotype.Service

@Service
class GuardSimulation {

    private fun parseObstructions(
        lines: List<String>
    ): Set<Vec2> = lines
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    '#' -> Vec2(x, y)
                    else -> null
                }
            }
        }
        .toSet()

    private fun parseInitialState(
        lines: List<String>
    ): GuardState = lines
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    '^' -> GuardState(
                        Vec2(x, y),
                        UP
                    )

                    else -> null
                }
            }
        }
        .first()

    fun countDistinctPositions(
        input: List<String>
    ): Int {
        val obstructions = parseObstructions(input)
        val initialState = parseInitialState(input)

        return countDistinctPositions(obstructions, initialState)!!
    }

    private fun countDistinctPositions(
        obstructions: Set<Vec2>,
        initialState: GuardState,
    ): Int? {

        val maxX = obstructions.maxOf { it.x }
        val maxY = obstructions.maxOf { it.y }

        val stateSet = mutableSetOf(initialState)
        var currentState = initialState
        while (currentState.position.x in 0..maxX && currentState.position.y in 0..maxY) {
            currentState = currentState.nextState(
                obstructions
            )

            if (currentState !in stateSet) {
                stateSet += currentState
            } else {
                return null
            }
        }

        return stateSet
            .map { it.position }
            .toSet()
            .size - 1
    }

    fun countObstructionPositionsForLoop(
        input: List<String>
    ): Int {
        val obstructions = parseObstructions(input)
        val initialState = parseInitialState(input)

        val maxX = obstructions.maxOf { it.x }
        val maxY = obstructions.maxOf { it.y }

        return (0..maxY)
            .flatMap { y ->
                (0..maxX).map { x ->
                    Vec2(x, y)
                }
            }
            .filter { it !in obstructions }
            .map { possibleObstruction ->
                countDistinctPositions(
                    obstructions + possibleObstruction,
                    initialState
                )
            }
            .filter { it == null }
            .size
    }

}