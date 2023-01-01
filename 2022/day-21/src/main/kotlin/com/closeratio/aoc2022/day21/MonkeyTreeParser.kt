package com.closeratio.aoc2022.day21

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2022.day21.Monkey.Id
import com.closeratio.aoc2022.day21.OperationMonkey.Operation.*
import org.springframework.stereotype.Component

@Component
class MonkeyTreeParser(
    private val resourceLoader: ResourceLoader
) {

    private fun loadMonkey(line: String): Monkey {
        val (idString, bodyString) = line.split(": ")
        val id = Id(idString)
        return if (bodyString.toLongOrNull() != null) {
            ConstantMonkey(
                id,
                bodyString.toLong()
            )
        } else {
            val (leftId, operationString, rightId) = bodyString.split(" ")
            OperationMonkey(
                id,
                Id(leftId),
                Id(rightId),
                when (operationString) {
                    "+" -> PLUS
                    "-" -> MINUS
                    "*" -> TIMES
                    "/" -> DIVIDE
                    else -> throw IllegalArgumentException("Unhandled operation: $operationString")
                }
            )
        }
    }

    fun parse(path: String): MonkeyTree = resourceLoader
        .loadResourceLines(path)
        .map(::loadMonkey)
        .associateBy(Monkey::id)
        .let(::MonkeyTree)

}

