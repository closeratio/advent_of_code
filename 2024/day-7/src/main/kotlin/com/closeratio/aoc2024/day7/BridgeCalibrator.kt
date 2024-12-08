package com.closeratio.aoc2024.day7

import org.springframework.stereotype.Service

@Service
class BridgeCalibrator(
    private val equationBuilder: EquationBuilder
) {

    fun calibrate(
        input: List<String>
    ): Long = input
        .mapNotNull { line ->
            val (testValueStr, termStr) = line.split(": ")
            val testValue = testValueStr.toLong()
            val equations = equationBuilder.generateEquations(
                termStr.split(" ").map { it.toLong() }
            )

            if (equations.any { it.compute() == testValue }) {
                testValue
            } else {
                null
            }
        }
        .sum()

}