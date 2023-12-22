package com.closeratio.aoc2022.day8

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class ForestParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseForest(path: String): Forest = resourceLoader
        .loadResourceLines(path)
        .mapIndexed { y, line ->
            line.mapIndexed { x, char ->
                Tree(Vec2(x, y), char.toString().toInt())
            }
        }
        .let(::Forest)

}
