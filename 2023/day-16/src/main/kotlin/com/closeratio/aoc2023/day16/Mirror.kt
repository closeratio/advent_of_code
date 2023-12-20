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
    ): Beam? = when (type) {
        HORIZONTAL -> reflectHorizontal(beam)
        VERTICAL -> reflectVertical(beam)
        BACKSLASH -> reflectBackslash(beam)
        FORWARD_SLASH -> reflectForwardSlash(beam)
    }

    private fun reflectHorizontal(
        beam: Beam
    ): Beam? {
        if (beam.currDirection == LEFT || beam.currDirection == RIGHT) {
            return null
        }

        beam.currDirection = LEFT

        return Beam(
            UUID.randomUUID(),
            beam.positions.toMutableList(),
            RIGHT
        )
    }

    private fun reflectVertical(
        beam: Beam
    ): Beam? {
        if (beam.currDirection == UP || beam.currDirection == DOWN) {
            return null
        }

        beam.currDirection = UP

        return Beam(
            UUID.randomUUID(),
            beam.positions.toMutableList(),
            DOWN
        )
    }

    private fun reflectBackslash(
        beam: Beam
    ): Beam? {
        when (beam.currDirection) {
            UP -> beam.currDirection = LEFT
            RIGHT -> beam.currDirection = DOWN
            DOWN -> beam.currDirection = RIGHT
            else -> beam.currDirection = UP
        }

        return null
    }

    private fun reflectForwardSlash(
        beam: Beam
    ): Beam? {
        when (beam.currDirection) {
            UP -> beam.currDirection = RIGHT
            RIGHT -> beam.currDirection = UP
            DOWN -> beam.currDirection = LEFT
            else -> beam.currDirection = DOWN
        }

        return null
    }

}

