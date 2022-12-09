package com.closeratio.aoc2022.day9

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2.Companion.ZERO
import com.closeratio.aoc2022.day9.Direction.*
import org.springframework.stereotype.Component

@Component
class MotionProcessor(
    private val resourceLoader: ResourceLoader
) {

    private fun parseMotion(path: String): List<Motion> = resourceLoader
        .loadResourceLines(path)
        .map { line ->
            val (dirString, amountString) = line.split(" ")
            Motion(
                when (dirString) {
                    "U" -> UP
                    "D" -> DOWN
                    "L" -> LEFT
                    "R" -> RIGHT
                    else -> throw IllegalArgumentException("Unkown direction: $dirString")
                },
                amountString.toInt()
            )
        }

    fun computeTailPositionCount(
        path: String,
        knots: Int = 2
    ): Int {
        // Break down the instructions into discrete motions
        val motions = parseMotion(path)
            .flatMap { (direction, amount) ->
                (1..amount).map {
                    direction
                }
            }

        // Initialise the rop so that every RopeLink is at the origin
        val initialRope = Rope((0 until (knots - 1)).map { index -> RopeLink(ZERO, ZERO, index) })

        // Apply each motion to each successive rope state
        val ropeStates = motions
            .fold(listOf(initialRope)) { acc, curr ->
                val moved = acc.last().move(curr)
                acc + moved
            }

        // Take the tail of the last link from every rope state and create
        return ropeStates
            .map { rope -> rope.ropeLinks.last() }
            .map(RopeLink::tail)
            .toSet()
            .size
    }

}
