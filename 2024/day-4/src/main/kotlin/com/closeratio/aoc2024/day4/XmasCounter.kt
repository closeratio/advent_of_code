package com.closeratio.aoc2024.day4

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2024.day4.Direction.*
import org.springframework.stereotype.Service

@Service
class XmasCounter {

    fun countXmas(input: List<String>): Int {
        val charMap = input
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Vec2(x, y) to char
                }
            }
            .toMap()

        val instances = charMap
            .entries
            .filter { (_, c) -> c == 'X' }
            .flatMap { (v, _) ->
                Direction.entries.map { dir ->
                    listOf(
                        v,
                        dir.apply(v),
                        dir.apply(dir.apply(v)),
                        dir.apply(dir.apply(dir.apply(v)))
                    ).mapNotNull { charMap[it] }
                }
            }
            .filter { it.size == 4 }
            .filter { it.joinToString("") == "XMAS" }

        return instances.size
    }

    fun countXShapedMas(input: List<String>): Int {
        val charMap = input
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Vec2(x, y) to char
                }
            }
            .toMap()

        val instances = charMap
            .entries
            .filter { (_, c) -> c == 'A' }
            .filter { (v, _) ->
                val firstLine = listOf(
                    DOWN_LEFT.apply(v),
                    v,
                    UP_RIGHT.apply(v)
                ).mapNotNull { charMap[it] }.joinToString("")

                val secondLine = listOf(
                    UP_LEFT.apply(v),
                    v,
                    DOWN_RIGHT.apply(v)
                ).mapNotNull { charMap[it] }.joinToString("")

                (firstLine == "MAS" || firstLine == "SAM") && (secondLine == "MAS" || secondLine == "SAM")
            }

        return instances.size
    }

}