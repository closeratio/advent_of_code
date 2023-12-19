package com.closeratio.aoc2023.day16

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day16.Direction.*
import java.util.*

class Beam(
    val id: UUID,
    val positions: MutableList<Vec2>,
    var currDirection: Direction
) {

    private var retreading: Boolean = false

    fun move(
        cave: Cave,
        beamHistory: Set<BeamHistory>
    ): Set<Beam> {
        if (isFinished(cave)) {
            return setOf(this)
        }

        val last = positions.last()

        val next = when (currDirection) {
            UP -> last.up()
            DOWN -> last.down()
            RIGHT -> last.right()
            LEFT -> last.left()
        }

        if (BeamHistory(next, currDirection) in beamHistory) {
            retreading = true
            return setOf(this)
        }

        positions += next

        return if (next in cave.mirrors) {
            cave.mirrors.getValue(next).reflect(this)
        } else {
            setOf(this)
        }
    }

    fun isFinished(cave: Cave): Boolean {
        if (positions.size == 1) {
            return false
        }

        if (retreading) {
            return true
        }

        val last = positions.last()
        return last.x < cave.minX || last.x > cave.maxX || last.y < cave.minY || last.y > cave.maxY
    }

    fun energisedTiles(cave: Cave): Set<Vec2> = positions
        .filter { it.x >= cave.minX && it.x <= cave.maxX && it.y >= cave.minY && it.y <= cave.maxY }
        .toSet()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Beam

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Beam(id=$id)"
    }

}

