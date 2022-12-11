package com.closeratio.aoc2022.day11

import com.closeratio.aoc2022.day11.Monkey.Id

data class MonkeySimulation(
    val monkeys: Map<Id, Monkey>,
    private val decreaseWorryLevel: Boolean = true
) {

    private fun inspectItems(monkey: Monkey) {
        val uninspectedItems = listOf(*monkey.items.toTypedArray())
        monkey.items.clear()

        val inspectedItems = uninspectedItems
            .map(monkey::inspect)
            .map {
                if (decreaseWorryLevel) Item(it.worryLevel / 3L) else it
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
            if (it == 999) {
                println("done")
            }
        }

        val (firstMostActive, secondMostActive) = monkeys
            .values
            .sortedByDescending(Monkey::inspectionCount)
            .take(2)

        return firstMostActive.inspectionCount * secondMostActive.inspectionCount
    }

}
