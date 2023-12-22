package com.closeratio.aoc2023.day10

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day10.PipeType.*
import org.springframework.stereotype.Component

@Component
class PipeParser {

    fun parse(
        lines: List<String>
    ): PipeAnalyser {
        // Get pipes that are easy to identify
        val pipes = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                val pos = Vec2(x, y)
                when (char) {
                    '|' -> Pipe(pos, VERTICAL)
                    '-' -> Pipe(pos, HORIZONTAL)
                    'L' -> Pipe(pos, L_BEND)
                    'J' -> Pipe(pos, J_BEND)
                    '7' -> Pipe(pos, SEVEN_BEND)
                    'F' -> Pipe(pos, F_BEND)
                    else -> null
                }
            }
        }.associateBy(Pipe::position)

        // Find starting pos
        val startPos = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                val pos = Vec2(x, y)
                when (char) {
                    'S' -> pos
                    else -> null
                }
            }
        }.first()

        // Find pipes that are connected to starting pos. There should only be two
        val adjacentToStart = startPos
            .immediatelyAdjacent()
            .mapNotNull { pipes[it] }
            .mapNotNull {
                val (first, second) = it.possibleConnectedPositions()
                if (first == startPos || second == startPos) it.position else null
            }
            .toSet()

        // Figure out starting pipe by checking each type of pipe to see whether it "fits".
        val startPipe = PipeType.entries
            .map { Pipe(startPos, it) }
            .first {
                val (first, second) = it.possibleConnectedPositions()
                first in adjacentToStart && second in adjacentToStart
            }

        return PipeAnalyser(
            startPos,
            pipes + mapOf(startPipe.position to startPipe)
        )
    }

}
