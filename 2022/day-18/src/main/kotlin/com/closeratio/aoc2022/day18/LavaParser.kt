package com.closeratio.aoc2022.day18

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec3
import org.springframework.stereotype.Component

@Component
class LavaParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseLava(path: String): Lava = resourceLoader
        .loadResourceLines(path)
        .map { it.split(",") }
        .map { (x, y, z) -> Vec3(x.toLong(), y.toLong(), z.toLong()) }
        .toSet()
        .let(::Lava)

}
