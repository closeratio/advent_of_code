package com.closeratio.aoc2023.day3

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class SchematicAnalyser {

    private fun parseSchematic(input: List<String>): EngineSchematic = input
        .flatMapIndexed { y, line ->
            line.mapIndexed { x, char ->
                Vec2(x, y) to char.toString()
            }
        }
        .toMap()
        .filterValues { it != "." }
        .let(::EngineSchematic)

    fun partNumberSum(input: List<String>): Long = parseSchematic(input)
        .partNumberSum()

    fun gearRatioSum(input: List<String>): Long = parseSchematic(input)
        .gearRatioSum()

}
