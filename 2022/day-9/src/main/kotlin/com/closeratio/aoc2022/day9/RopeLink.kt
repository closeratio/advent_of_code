package com.closeratio.aoc2022.day9

import com.closeratio.aoc.common.math.Vec2

data class RopeLink(
    val head: Vec2,
    val tail: Vec2,
    val index: Int
) {

    fun move(newHeadPos: Vec2): RopeLink {
        if (newHeadPos == tail) {
            // Overlapping, so tail doesn't move
            return RopeLink(newHeadPos, tail, index)
        } else if (newHeadPos.isAdjacent(tail, true)) {
            // Within a single distance unit including diagonally, so tail doesn't move
            return RopeLink(newHeadPos, tail, index)
        }

        val manhattanDistance = newHeadPos.manhattanDistance(tail)
        if (manhattanDistance == 2L) {
            // Head must be above, below, left or right of the tail, so move in one of those directions (whichever is closest)
            return RopeLink(
                newHeadPos,
                tail.immediatelyAdjacent().minBy { it.manhattanDistance(newHeadPos) },
                index
            )
        } else if (manhattanDistance <= 4L) {
            // Either an "L shape" away or 2 spaces diagonally, so tail has to move diagonally
            return RopeLink(
                newHeadPos,
                tail.diagonals().minBy { it.manhattanDistance(newHeadPos) },
                index
            )
        }

        throw IllegalStateException("Head has moved too far from tail (head=$newHeadPos, tail=$tail)")
    }

}
