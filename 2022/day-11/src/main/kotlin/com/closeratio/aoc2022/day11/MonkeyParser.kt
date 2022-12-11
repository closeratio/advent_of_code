package com.closeratio.aoc2022.day11

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc2022.day11.Monkey.Id
import org.springframework.stereotype.Component

@Component
class MonkeyParser(
    private val resourceLoader: ResourceLoader
) {

    private fun parseWorryModifier(line: String): (Item) -> Item {
        val (operationString, rightTermString) = line.split("=")
            .last()
            .trim()
            .split(" ")
            .takeLast(2)

        val operation: (Long, Long) -> Long = when (operationString) {
            "+" -> { left, right -> left + right }
            "*" -> { left, right -> left * right }
            else -> throw IllegalArgumentException("Unknown operation: $operationString")
        }

        val rightTerm = rightTermString.toLongOrNull()
        val rightTermFunction: (Item) -> Long = when (rightTermString) {
            "old" -> { it: Item -> it.worryLevel }
            else -> { _ -> rightTerm!! }
        }

        return { item ->
            Item(
                operation(
                    item.worryLevel,
                    rightTermFunction(item)
                )
            )
        }
    }

    private fun parseThrowFunction(lines: List<String>): (Item) -> Id {
        val (testLine, trueLine, falseLine) = lines

        val divisor = testLine
            .split(" ")
            .last()
            .toLong()

        val trueMonkeyId = trueLine
            .split(" ")
            .last()
            .toInt()
            .let(::Id)

        val falseMonkeyId = falseLine
            .split(" ")
            .last()
            .toInt()
            .let(::Id)

        return { item ->
            if ((item.worryLevel % divisor) == 0L) trueMonkeyId else falseMonkeyId
        }
    }

    fun parseInput(path: String): MonkeySimulation = resourceLoader
        .loadResourceLines(path)
        .chunked(7)
        .map { lines ->
            val id: Id = lines[0].split(" ")[1].dropLast(1).toInt().let(Monkey::Id)
            val items: MutableList<Item> = lines[1].split(":")[1]
                .trim()
                .split(",")
                .map(String::trim)
                .map(String::toLong)
                .map(::Item)
                .toMutableList()

            Monkey(
                id,
                items,
                parseWorryModifier(lines[2]),
                parseThrowFunction(lines.takeLast(3))
            )
        }
        .let {
            MonkeySimulation(it.associateBy(Monkey::id))
        }

}

