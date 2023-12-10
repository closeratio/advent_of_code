package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.math.Vec2
import java.util.*
import kotlin.math.min

class PipeAnalyser(
    val start: Vec2,
    val pipes: Map<Vec2, Pipe>
) {

    fun farthestDistanceFromStart(): Long {
        val distanceMap = mutableMapOf<Vec2, Long>(
            start to 0
        )
        val visited = mutableSetOf<Vec2>()
        val visitQueue = LinkedList(listOf(start))

        while (visitQueue.isNotEmpty()) {
            val pos = visitQueue.poll()
            val currDistance = distanceMap.getValue(pos)
            visited += pos

            visitQueue += pipes
                .getValue(pos)
                .possibleConnectedPositions()
                .toList()
                .onEach {
                    distanceMap[it] = min(
                        currDistance + 1,
                        distanceMap[it] ?: Long.MAX_VALUE
                    )
                }
                .filter { it !in visited }
        }

        return distanceMap.values.max()
    }

}