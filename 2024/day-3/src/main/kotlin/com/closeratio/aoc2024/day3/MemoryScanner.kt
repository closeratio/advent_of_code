package com.closeratio.aoc2024.day3

import com.closeratio.aoc2024.day3.instruction.DoInstruction
import com.closeratio.aoc2024.day3.instruction.DontInstruction
import com.closeratio.aoc2024.day3.instruction.MulInstruction
import org.springframework.stereotype.Service

@Service
class MemoryScanner {

    private val mulRegex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
    private val doRegex = """do\(\)""".toRegex()
    private val dontRegex = """don't\(\)""".toRegex()

    fun sumMulInstructions(input: String): Long = findMulInstructions(input).sumOf { it.product }

    fun sumEnabledMulInstructions(input: String): Long {
        val instructions = (
                findMulInstructions(input) +
                        findDoInstructions(input) +
                        findDontInstructions(input)
                ).sorted()

        val (total) = instructions.fold(
            0L to true
        ) { acc, curr ->
            when (curr) {
                is DoInstruction -> acc.first to true
                is DontInstruction -> acc.first to false
                is MulInstruction -> {
                    if (acc.second) {
                        acc.first + curr.product to true
                    } else {
                        acc.first to false
                    }
                }

                else -> throw IllegalArgumentException("Unknown instruction type ${curr.javaClass}")
            }
        }

        return total
    }

    private fun findMulInstructions(input: String): List<MulInstruction> = mulRegex
        .findAll(input)
        .map { result ->
            val matched = result.groupValues.first()
            val (lString, rString) = matched.drop(4).dropLast(1).split(",")
            MulInstruction(
                result.range,
                lString.toLong(),
                rString.toLong()
            )
        }
        .toList()

    private fun findDoInstructions(input: String): List<DoInstruction> = doRegex
        .findAll(input)
        .map { result ->
            DoInstruction(
                result.range
            )
        }
        .toList()

    private fun findDontInstructions(input: String): List<DontInstruction> = dontRegex
        .findAll(input)
        .map { result ->
            DontInstruction(
                result.range
            )
        }
        .toList()

}