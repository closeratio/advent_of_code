package com.closeratio.aoc2024.day7

import org.springframework.stereotype.Service

@Service
class BridgeCalibrator(
    private val equationSetBuilder: EquationSetBuilder
) {

    fun calibrate(
        input: List<String>,
        includeConcat: Boolean = false
    ): Long = input
        .mapNotNull { line ->
            val (testValueStr, termStr) = line.split(": ")
            val testValue = testValueStr.toLong()
            val equations = equationSetBuilder.generateEquations(termStr, includeConcat)

            if (equations.any { it.compute() == testValue }) {
                testValue
            } else {
                null
            }
        }
        .sum()

}