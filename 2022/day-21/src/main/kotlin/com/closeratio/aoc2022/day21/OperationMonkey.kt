package com.closeratio.aoc2022.day21

import com.closeratio.aoc2022.day21.OperationMonkey.Operation.*

class OperationMonkey(
    id: Id,
    private val first: Id,
    private val second: Id,
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
        return when(operation) {
            PLUS -> firstNumber + secondNumber
            MINUS -> firstNumber - secondNumber
            TIMES -> firstNumber * secondNumber
            DIVIDE -> firstNumber / secondNumber
        }
    }
}