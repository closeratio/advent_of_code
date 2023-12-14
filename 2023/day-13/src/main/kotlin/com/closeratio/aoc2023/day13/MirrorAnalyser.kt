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
    ): List<Pair<String, Mirror>> = input
        .split("\n\n")
        .map(String::trim)
        .map { it to parseMirror(it) }

    fun summarise(
        input: String
    ): Long = parseMirrors(input)
        .map { (string, mirror) ->
            val result = mirror.summarize()
            if (result == null) {
                println("Unable to find symmetry in:\n$string")
                throw IllegalStateException("No symmetry")
            }
            result
        }
        .sum()

}

