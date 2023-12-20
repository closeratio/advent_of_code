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

    fun project(
        cave: Cave,
        beamHistory: MutableSet<BeamHistory>,
        beams: MutableList<Beam>
    ) {
        while (!isFinished(cave)) {
            val last = positions.last()

            val next = when (currDirection) {
                UP -> last.up()
                DOWN -> last.down()
                RIGHT -> last.right()
                LEFT -> last.left()
            }

            val history = BeamHistory(next, currDirection)
            if (history in beamHistory) {
                retreading = true
            } else {
                beamHistory += history
                positions += next

                if (next in cave.mirrors) {
                    val additionalBeam = cave.mirrors.getValue(next).reflect(this)
                    if (additionalBeam != null) {
                        beams.add(additionalBeam)
                    }
                }
            }
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

