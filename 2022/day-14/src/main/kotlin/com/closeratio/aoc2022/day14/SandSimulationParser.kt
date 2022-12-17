package com.closeratio.aoc2022.day14

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class SandSimulationParser(
    private val resourceLoader: ResourceLoader
) {

    fun parseRocks(line: String): Set<Vec2> = line
        .split(" -> ")
        .map { vecString -> vecString.split(",").let { (x, y) -> Vec2(x.toLong(), y.toLong()) } }
        .windowed(2) { (first, second) -> first.lineTo(second) }
        .flatten()
        .toSet()

    fun parseSandSimulation(path: String): SandSimulation = resourceLoader
        .loadResourceLines(path)
        .flatMap(::parseRocks)
        .toSet()
        .let(::SandSimulation)

}
