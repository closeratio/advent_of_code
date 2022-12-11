package com.closeratio.aoc2022.day11

import com.closeratio.aoc2022.day11.Monkey.Id

data class MonkeySimulation(
    val monkeys: Map<Id, Monkey>
) {

    private fun inspectItems(monkey: Monkey) {
        val uninspectedItems = listOf(*monkey.items.toTypedArray())
        monkey.items.clear()

        val inspectedItems = uninspectedItems
            .map(monkey::inspect)
            .map {
                Item(it.worryLevel / 3)
            }
            .map {
                it to monkey.throwTarget(it)
            }

        inspectedItems.forEach { (item, throwTarget) ->
            monkeys.getValue(throwTarget).items += item
        }
    }

    fun calculateMonkeyBusiness(roundCount: Int): Long {
        val keys = monkeys
            .keys
            .sortedBy(Id::value)

        repeat(roundCount) {
            keys.forEach {
                inspectItems(monkeys.getValue(it))
            }
        }

        val (firstMostActive, secondMostActive) = monkeys
            .values
            .sortedByDescending(Monkey::inspectionCount)
            .take(2)

        return firstMostActive.inspectionCount * secondMostActive.inspectionCount
    }

}
