package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
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
        lines: List<String>,
        initialDirection: Direction = RIGHT
    ): Long {
        val mirrors = parseMirrors(lines)
        val cave = Cave(
            0,
            0,
            mirrors.maxOf { it.pos.x },
            mirrors.maxOf { it.pos.y },
            mirrors.associateBy(Mirror::pos)
        )

        val beams = mutableMapOf(
            UUID.randomUUID().let {
                it to Beam(
                    it,
                    arrayListOf(
                        ZERO
                    ),
                    initialDirection
                )
            }
        )

        val beamHistory = mutableSetOf(
            BeamHistory(ZERO, initialDirection)
        )

        while (!beams.values.all { it.isFinished(cave) }) {
            val updatedBeams = beams.values
                .flatMap { it.move(cave, beamHistory) }
                .associateBy { it.id }

            beams += updatedBeams

            beamHistory += beams
                .values
                .map { BeamHistory(it.positions.last(), it.currDirection) }
        }

        val energised = beams
            .values
            .flatMap { it.energisedTiles(cave) }
            .toSet()

        println((cave.minY..cave.maxY).joinToString("\n") { y ->
            (cave.minX..cave.maxX).joinToString("") { x ->
                if (Vec2(x, y) in energised) "#" else "."
            }
        } + "\n")

        return beams
            .values
            .flatMap { it.energisedTiles(cave) }
            .toSet()
            .size
            .toLong()
    }

}
