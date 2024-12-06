package com.closeratio.aoc2024.day4

import com.closeratio.aoc.common.math.Vec2
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

}