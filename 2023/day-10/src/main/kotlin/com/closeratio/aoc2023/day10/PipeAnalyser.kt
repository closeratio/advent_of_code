package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.math.Vec2
import java.util.*
import kotlin.math.min

class PipeAnalyser(
    val start: Vec2,
    val pipes: Map<Vec2, Pipe>
) {

    data class FarthestDistanceResult(
        val value: Long,
        val visited: Set<Vec2>
    )

    fun farthestDistanceFromStart(): FarthestDistanceResult {
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

        return FarthestDistanceResult(
            distanceMap.values.max(),
            visited
        )
    }

    fun calculateTilesEnclosedByLoop(): Long {
        val scaledLoop = farthestDistanceFromStart()
            .visited
            .map { it * 2 to pipes.getValue(it) }
            .flatMap { (scaled, originalPipe) ->
                val newPipe = Pipe(scaled, originalPipe.type)
                newPipe.possibleConnectedPositions().toList() + scaled
            }
            .toSet()

        val xRange = scaledLoop.minOf(Vec2::x)..scaledLoop.maxOf(Vec2::x)
        val yRange = scaledLoop.minOf(Vec2::y)..scaledLoop.maxOf(Vec2::y)

        val possibleInteriorGround = yRange
            .filter { it % 2 == 0L } // We only care about the original tiles, not the ones that we've had to "create"
            .flatMap { y ->
                xRange
                    .filter { it % 2 == 0L }
                    .mapNotNull { x ->
                        val pos = Vec2(x, y)
                        if (pos !in scaledLoop) pos else null
                    }
            }

        val knownInteriorGround = mutableSetOf<Vec2>()
        val knownExteriorGround = mutableSetOf<Vec2>()
        possibleInteriorGround
            .sortedBy { it.x }
            .sortedBy { it.y }
            .forEach { possibleTile ->
                val visited = mutableSetOf<Vec2>()
                val visitQueue = LinkedHashSet(listOf(possibleTile))

                while (visitQueue.isNotEmpty()) {
                    val currPos = visitQueue.first()
                    visitQueue.remove(currPos)
                    visited += currPos

                    if (currPos in knownInteriorGround) {
                        knownInteriorGround += possibleTile
                        break
                    } else if (currPos in knownExteriorGround || currPos.x !in xRange || currPos.y !in yRange) {
                        knownExteriorGround += possibleTile
                        break
                    } else {
                        visitQueue += currPos
                            .immediatelyAdjacent()
                            .filter { it !in visited }
                            .filter { it !in scaledLoop }
                    }
                }

                if (possibleTile !in knownExteriorGround) {
                    knownInteriorGround += possibleTile
                }
            }

        return knownInteriorGround.size.toLong()
    }

}