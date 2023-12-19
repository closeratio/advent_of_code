package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day16.Direction.*
import com.closeratio.aoc2023.day16.MirrorType.*
import java.util.*

data class Mirror(
    val pos: Vec2,
    val type: MirrorType
) {

    fun reflect(
        beam: Beam
    ): Set<Beam> = when (type) {
        HORIZONTAL -> reflectHorizontal(beam)
        VERTICAL -> reflectVertical(beam)
        BACKSLASH -> reflectBackslash(beam)
        FORWARD_SLASH -> reflectForwardSlash(beam)
    }

    private fun reflectHorizontal(
        beam: Beam
    ): Set<Beam> {
        if (beam.currDirection == LEFT || beam.currDirection == RIGHT) {
            return setOf(beam)
        }

        return setOf(
            Beam(
                beam.id,
                beam.positions.toMutableList(),
                LEFT
            ),
            Beam(
                UUID.randomUUID(),
                beam.positions.toMutableList(),
                RIGHT
            )
        )
    }

    private fun reflectVertical(
        beam: Beam
    ): Set<Beam> {
        if (beam.currDirection == UP || beam.currDirection == DOWN) {
            return setOf(beam)
        }

        return setOf(
            Beam(
                beam.id,
                beam.positions.toMutableList(),
                UP
            ),
            Beam(
                UUID.randomUUID(),
                beam.positions.toMutableList(),
                DOWN
            )
        )
    }

    private fun reflectBackslash(
        beam: Beam
    ): Set<Beam> {
        when (beam.currDirection) {
            UP -> beam.currDirection = LEFT
            RIGHT -> beam.currDirection = DOWN
            DOWN -> beam.currDirection = RIGHT
            else -> beam.currDirection = UP
        }

        return setOf(beam)
    }

    private fun reflectForwardSlash(
        beam: Beam
    ): Set<Beam> {
        when (beam.currDirection) {
            UP -> beam.currDirection = RIGHT
            RIGHT -> beam.currDirection = UP
            DOWN -> beam.currDirection = LEFT
            else -> beam.currDirection = DOWN
        }

        return setOf(beam)
    }

}

