package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day16.Direction.*
import com.closeratio.aoc2023.day16.MirrorType.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class BeamAnalyser {

    private fun parseMirrors(
        lines: List<String>
    ): Set<Mirror> = lines
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                val pos = Vec2(x.toLong(), y.toLong())
                when (char) {
                    '-' -> Mirror(pos, HORIZONTAL)
                    '|' -> Mirror(pos, VERTICAL)
                    '/' -> Mirror(pos, FORWARD_SLASH)
                    '\\' -> Mirror(pos, BACKSLASH)
                    else -> null
                }
            }
        }
        .toSet()

    fun computeEnergisedCount(
        lines: List<String>,
        startPos: Vec2 = Vec2(-1, 0),
        startDirection: Direction = RIGHT
    ): Long {
        val mirrors = parseMirrors(lines)
        val cave = Cave(
            0,
            0,
            mirrors.maxOf { it.pos.x },
            mirrors.maxOf { it.pos.y },
            mirrors.associateBy(Mirror::pos)
        )

        val unfinishedBeams = LinkedList(
            listOf(
                Beam(
                    UUID.randomUUID(),
                    arrayListOf(
                        startPos
                    ),
                    startDirection
                )
            )
        )
        val finishedBeams = LinkedList<Beam>()

        val beamHistory = mutableSetOf(
            BeamHistory(startPos, startDirection)
        )

        while (unfinishedBeams.isNotEmpty()) {
            val beam = unfinishedBeams.poll()
            beam.project(
                cave,
                beamHistory,
                unfinishedBeams
            )
            finishedBeams += beam
        }

        return finishedBeams
            .flatMap { it.energisedTiles(cave) }
            .toSet()
            .size
            .toLong()
    }

    fun computeOptimalDirection(
        lines: List<String>
    ): Long {
        val mirrors = parseMirrors(lines)
        val maxX = mirrors.maxOf { it.pos.x }
        val maxY = mirrors.maxOf { it.pos.y }

        val startingConfigurations = (0..maxX).flatMap { x ->
            listOf(
                Vec2(x, -1) to DOWN,
                Vec2(x, maxY + 1) to UP
            )
        } + (0..maxY).flatMap { y ->
            listOf(
                Vec2(-1, y) to RIGHT,
                Vec2(maxX + 1, y) to LEFT
            )
        }

        return startingConfigurations
            .maxOf { (startPos, startDir) ->
                computeEnergisedCount(
                    lines,
                    startPos,
                    startDir
                )
            }
    }

}
