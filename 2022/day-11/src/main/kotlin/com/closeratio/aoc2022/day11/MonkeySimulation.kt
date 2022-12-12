package com.closeratio.aoc2022.day11

import com.closeratio.aoc2022.day11.Monkey.Id

data class MonkeySimulation(
    val monkeys: Map<Id, Monkey>,
    private val decreaseWorryLevel: Boolean = true
) {

    private val commonMultiple = monkeys
        .values
        .map(Monkey::divisor)
        .reduce(Long::times)

    private fun inspectItems(monkey: Monkey) {
        val uninspectedItems = listOf(*monkey.items.toTypedArray())
        monkey.items.clear()

        val inspectedItems = uninspectedItems
            .map(monkey::inspect)
            .map {
                if (decreaseWorryLevel) {
                    Item(it.worryLevel / 3L)
                } else {
                    Item(it.worryLevel % commonMultiple)
                }
            }

        inspectedItems.forEach { item ->
            monkeys.getValue(monkey.throwTarget(item)).items += item
        }
    }

    fun calculateMonkeyBusiness(roundCount: Int): Long {
        val keys = monkeys
            .keys
            .sortedBy(Id::value)

        repeat(roundCount) {
            keys.forEach { id ->
                inspectItems(monkeys.getValue(id))
            }
        }

        val (firstMostActive, secondMostActive) = monkeys
            .values
            .sortedByDescending(Monkey::inspectionCount)
            .take(2)

        return firstMostActive.inspectionCount * secondMostActive.inspectionCount
    }

}
