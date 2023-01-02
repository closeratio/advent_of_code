package com.closeratio.aoc2022.day21

import com.closeratio.aoc2022.day21.OperationMonkey.Operation.*

class OperationMonkey(
    id: Id,
    val first: Id,
    val second: Id,
    private val operation: Operation
): Monkey(id) {

    enum class Operation{
        PLUS,
        MINUS,
        TIMES,
        DIVIDE
    }

    override fun computeNumber(monkeyMap: Map<Id, Monkey>): Long {
        val firstNumber = monkeyMap.getValue(first).computeNumber(monkeyMap)
        val secondNumber = monkeyMap.getValue(second).computeNumber(monkeyMap)
        return when (operation) {
            PLUS -> firstNumber + secondNumber
            MINUS -> firstNumber - secondNumber
            TIMES -> firstNumber * secondNumber
            DIVIDE -> firstNumber / secondNumber
        }
    }

    override fun computeRequiredHumanNumber(
        monkeyMap: Map<Id, Monkey>,
        routeToHuman: Set<Id>,
        requiredOutputNumber: Long
    ): Long {
        if (first !in routeToHuman && second !in routeToHuman) {
            throw IllegalStateException("Cannot compute requited human number when human is not a child of this monkey")
        }

        val humanPathMonkeyId = if (first in routeToHuman) first else second
        val humanPathMonkey = monkeyMap.getValue(humanPathMonkeyId)

        val otherPathMonkey = monkeyMap.getValue(if (humanPathMonkeyId == first) second else first)
        val otherValue = otherPathMonkey.computeNumber(monkeyMap)

        val desiredValue = when (operation) {
            PLUS -> requiredOutputNumber - otherValue
            MINUS -> {
                if (humanPathMonkeyId == first) {
                    requiredOutputNumber + otherValue
                } else {
                    otherValue - requiredOutputNumber
                }
            }

            TIMES -> requiredOutputNumber / otherValue
            DIVIDE -> {
                if (humanPathMonkeyId == first) {
                    requiredOutputNumber * otherValue
                } else {
                    otherValue * requiredOutputNumber
                }
            }
        }

        return humanPathMonkey.computeRequiredHumanNumber(monkeyMap, routeToHuman, desiredValue)
    }

    override fun routeToMonkey(
        monkeyMap: Map<Id, Monkey>,
        id: Id
    ): Set<Id> {
        val firstRoute = monkeyMap.getValue(first).routeToMonkey(monkeyMap, id)
        val secondRoute = monkeyMap.getValue(second).routeToMonkey(monkeyMap, id)

        return when {
            firstRoute.isNotEmpty() -> firstRoute + this.id
            secondRoute.isNotEmpty() -> secondRoute + this.id
            else -> emptySet()
        }
    }
}
