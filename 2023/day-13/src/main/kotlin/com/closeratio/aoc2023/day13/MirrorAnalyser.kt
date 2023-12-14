package com.closeratio.aoc2023.day13

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class MirrorAnalyser {

    private fun parseMirror(
        input: String
    ): Mirror = input
        .split("\n")
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                when (char) {
                    '#' -> Vec2(x.toLong(), y.toLong())
                    else -> null
                }
            }
        }
        .let { Mirror(it.toSet()) }

    private fun parseMirrors(
        input: String
    ): List<Mirror> = input
        .split("\n\n")
        .map(String::trim)
        .map(::parseMirror)

    fun summarise(
        input: String
    ): Long = parseMirrors(input).sumOf {
        it.findSymmetryLine().first().summaryValue
    }

    private fun generateVariations(
        mirror: Mirror
    ): List<Mirror> = (0..mirror.maxY)
        .flatMap { y ->
            (0..mirror.maxX)
                .map { x ->
                    val vec = Vec2(x, y)
                    val rocks = if (vec in mirror.rocks) mirror.rocks - vec else mirror.rocks + vec
                    Mirror(rocks)
                }
        }

    fun summariseWithoutSmudge(
        input: String
    ): Long = parseMirrors(input).sumOf { mirror ->
        val originalLine = mirror.findSymmetryLine().first()

        generateVariations(mirror)
            .flatMap(Mirror::findSymmetryLine)
            .toSet()
            .toList()
            .firstOrNull { it != originalLine }
            ?.summaryValue
            ?: throw IllegalStateException("Unable to find another symmetry line:\n${mirror.generateString()}\n")
    }

}

