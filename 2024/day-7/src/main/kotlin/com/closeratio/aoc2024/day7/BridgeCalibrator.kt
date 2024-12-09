package com.closeratio.aoc2024.day7

import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.stereotype.Service
import java.util.concurrent.Callable
import java.util.concurrent.Future

@Service
class BridgeCalibrator(
    private val equationSetBuilder: EquationSetBuilder,
    private val asyncTaskExecutor: AsyncTaskExecutor
) {

    fun calibrate(
        input: List<String>,
        includeConcat: Boolean = false
    ): Long = input
        .map { line ->
            asyncTaskExecutor.submit(Callable<Long> {
                val (testValueStr, termStr) = line.split(": ")
                val testValue = testValueStr.toLong()
                val equations = equationSetBuilder.generateEquations(termStr, includeConcat)

                if (equations.any { it.compute() == testValue }) {
                    testValue
                } else {
                    null
                }
            })
        }
        .mapNotNull(Future<Long>::get)
        .sum()

}