package com.closeratio.aoc2022.day21

import com.closeratio.aoc2022.day21.Monkey.Id

class MonkeyTree(
    private val map: Map<Id, Monkey>
) {

    fun computeRootNumber(): Long = map
        .getValue(Id("root"))
        .computeNumber(map)

    fun computeRequiredHumanNumber(): Long {
        val root = map.getValue(Id("root")) as OperationMonkey
        val route = root.routeToMonkey(map, Id("humn"))

        val firstMonkey = map.getValue(root.first)
        val firstNum = firstMonkey.computeNumber(map)

        val secondMonkey = map.getValue(root.second)
        val secondNum = secondMonkey.computeNumber(map)

        return if (root.first in route) {
            firstMonkey.computeRequiredHumanNumber(map, route, secondNum)
        } else {
            secondMonkey.computeRequiredHumanNumber(map, route, firstNum)
        }
    }

}
