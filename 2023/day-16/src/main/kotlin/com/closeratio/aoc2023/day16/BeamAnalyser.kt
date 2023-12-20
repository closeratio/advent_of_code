package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day16.Direction.RIGHT
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
        lines: List<String>
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
                        Vec2(-1, 0)
                    ),
                    RIGHT
                )
            )
        )
        val finishedBeams = LinkedList<Beam>()

        val beamHistory = mutableSetOf(
            BeamHistory(Vec2(-1, 0), RIGHT)
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

}
