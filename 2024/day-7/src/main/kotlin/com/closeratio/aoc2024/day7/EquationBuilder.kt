package com.closeratio.aoc2024.day7

import com.closeratio.aoc2024.day7.Operator.MULTIPLY
import com.closeratio.aoc2024.day7.Operator.PLUS
import org.springframework.stereotype.Service

@Service
class EquationBuilder {

    fun generateEquations(
        terms: List<Long>
    ): List<Equation> = generatePermutations(
        terms.drop(1)
    ).map {
        listOf(PLUS to terms.first()) + it
    }.map(::Equation)

    private fun generatePermutations(
        remainingParts: List<Long>
    ): List<List<Pair<Operator, Long>>> {
        if (remainingParts.size == 1) {
            return listOf(
                listOf(
                    PLUS to remainingParts.first(),
                ),
                listOf(
                    MULTIPLY to remainingParts.first()
                )
            )
        }

        val term = remainingParts.first()
        val subPermutations = generatePermutations(remainingParts.drop(1))

        return listOf(
            listOf(PLUS to term),
            listOf(MULTIPLY to term)
        ).flatMap { pair ->
            subPermutations.map {
                pair + it
            }
        }
    }

}

