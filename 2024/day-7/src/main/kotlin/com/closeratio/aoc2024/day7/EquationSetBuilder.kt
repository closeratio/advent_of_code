package com.closeratio.aoc2024.day7

import com.closeratio.aoc2024.day7.Operator.*
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class EquationSetBuilder {

    fun generateEquations(
        termStr: String,
        includeConcat: Boolean
    ): List<Equation> {
        val values = termStr.split(" ")
        val permutationBase = if (includeConcat) 3 else 2

        val permutationCount = permutationBase.toDouble().pow(values.size - 1).toLong()

        val permutationStrings = (0..<permutationCount).map { permutationIndex ->
            val permutationString = permutationIndex
                .toString(permutationBase)
                .let {
                    if (it.length < values.size - 1) {
                        it.padStart(values.size - 1, '0')
                    } else {
                        it
                    }
                }
            val operatorStrings = permutationString
                .map { char ->
                    when (char) {
                        '0' -> " + "
                        '1' -> " * "
                        '2' -> " || "
                        else -> throw IllegalArgumentException("Unsupported character: $char")
                    }
                } + ""

            values.zip(operatorStrings).joinToString("") { (l, r) -> l + r }
        }

        return permutationStrings.map(::parseEquestion)
    }

    private fun parseEquestion(
        equationStr: String
    ): Equation = equationStr
        .split(" ")
        .map { elementStr ->
            when (elementStr) {
                "+" -> PLUS
                "*" -> MULTIPLY
                "||" -> CONCATENATION
                else -> Value(elementStr.toLong())
            }
        }
        .let(::Equation)

}

