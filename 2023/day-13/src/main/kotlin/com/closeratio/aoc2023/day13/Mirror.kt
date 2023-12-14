package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2023.day13.SymmetryLine.Orientation.HORIZONTAL
import com.closeratio.aoc2023.day13.SymmetryLine.Orientation.VERTICAL
import kotlin.math.abs
import kotlin.math.min

class Mirror(
    val rocks: Set<Vec2>
) {

    val maxX = rocks.maxOf(Vec2::x)
    val maxY = rocks.maxOf(Vec2::y)

    private fun findXSymmetry(x: Long): SymmetryLine? {
        val leftOfLine = rocks.filter { it.x <= x }
        val rightOfLine = rocks.filter { it.x > x }

        val width = min(x + 1, maxX - x)

        val leftCandidates = leftOfLine
            .filter { it.x > x - width }
            .map { Vec2(abs(it.x - x), it.y) }
            .toSet()
        val rightCandidates = rightOfLine
            .filter { it.x <= x + width }
            .map { Vec2(it.x - x - 1, it.y) }
            .toSet()

        return if (leftCandidates.size == rightCandidates.size && (leftCandidates - rightCandidates).isEmpty()) {
            SymmetryLine(
                x + 1,
                VERTICAL
            )
        } else {
            null
        }
    }

    private fun findAllXSymmetry(): Set<SymmetryLine> = setOfNotNull(
        (0..<maxX).firstNotNullOfOrNull { findXSymmetry(it) },
        ((maxX - 1).downTo(0)).firstNotNullOfOrNull { findXSymmetry(it) }
    )

    private fun findYSymmetry(y: Long): SymmetryLine? {
        val aboveLine = rocks.filter { it.y <= y }
        val belowLine = rocks.filter { it.y > y }

        val height = min(y + 1, maxY - y)

        val aboveCandidates = aboveLine
            .filter { it.y > y - height }
            .map { Vec2(it.x, abs(it.y - y)) }
            .toSet()
        val belowCandidates = belowLine
            .filter { it.y <= y + height }
            .map { Vec2(it.x, it.y - y - 1) }
            .toSet()

        return if (aboveCandidates.size == belowCandidates.size && (aboveCandidates - belowCandidates).isEmpty()) {
            SymmetryLine(
                y + 1,
                HORIZONTAL
            )
        } else {
            null
        }
    }

    private fun findAllYSymmetry(): Set<SymmetryLine> = setOfNotNull(
        (0..<maxY).firstNotNullOfOrNull { findYSymmetry(it) },
        ((maxY - 1).downTo(0)).firstNotNullOfOrNull { findYSymmetry(it) }
    )

    fun findSymmetryLine(): List<SymmetryLine> = (findAllXSymmetry() + findAllYSymmetry()).toList()

    fun generateString(): String = (0..maxY).joinToString("\n") { y ->
        (0..maxX).joinToString("") { x ->
            if (Vec2(x, y) in rocks) "#" else "."
        }
    }

}
