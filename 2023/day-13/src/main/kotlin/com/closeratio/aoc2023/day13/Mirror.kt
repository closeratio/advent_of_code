package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.math.Vec2
import kotlin.math.abs
import kotlin.math.min

class Mirror(
    val rocks: Set<Vec2>
) {

    private val maxX = rocks.maxOf(Vec2::x)
    private val maxY = rocks.maxOf(Vec2::y)

    private fun findXSymmetry(): Long? = (0..<maxX).firstNotNullOfOrNull { x ->
        val leftOfLine = rocks.filter { it.x <= x }
        val rightOfLine = rocks.filter { it.x > x }

        val width = min(
            (leftOfLine.maxOf(Vec2::x) - leftOfLine.minOf(Vec2::x)) + 1,
            (rightOfLine.maxOf(Vec2::x) - rightOfLine.minOf(Vec2::x)) + 1
        )

        val leftCandidates = leftOfLine
            .filter { it.x > x - width }
            .map { Vec2(abs(it.x - x), it.y) }
            .toSet()
        val rightCandidates = rightOfLine
            .filter { it.x <= x + width }
            .map { Vec2(it.x - x - 1, it.y) }
            .toSet()

        if (leftCandidates.size == rightCandidates.size && (leftCandidates - rightCandidates).isEmpty()) {
            x + 1
        } else {
            null
        }
    }

    private fun findYSummetry(): Long? = (0..<maxY).firstNotNullOfOrNull { y ->
        val aboveLine = rocks.filter { it.y <= y }
        val belowLine = rocks.filter { it.y > y }

        val height = min(
            (aboveLine.maxOf(Vec2::y) - aboveLine.minOf(Vec2::y)) + 1,
            (belowLine.maxOf(Vec2::y) - belowLine.minOf(Vec2::y)) + 1
        )

        val aboveCandidates = aboveLine
            .filter { it.y > y - height }
            .map { Vec2(it.x, abs(it.y - y)) }
            .toSet()
        val belowCandidates = belowLine
            .filter { it.y <= y + height }
            .map { Vec2(it.x, it.y - y - 1) }
            .toSet()

        if (aboveCandidates.size == belowCandidates.size && (aboveCandidates - belowCandidates).isEmpty()) {
            (y + 1) * 100
        } else {
            null
        }
    }

    fun summarize(): Long? = findXSymmetry() ?: findYSummetry()

}
