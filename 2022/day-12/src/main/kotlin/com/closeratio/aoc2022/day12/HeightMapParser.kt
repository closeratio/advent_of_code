package com.closeratio.aoc2022.day12

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class HeightMapParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseHeightMap(path: String): HeightMap = resourceLoader
        .loadResourceLines(path)
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char ->
                Tile(
                    Vec2(x.toLong(), y.toLong()),
                    char.toString()
                )
            }
        }
        .let(::HeightMap)


}
