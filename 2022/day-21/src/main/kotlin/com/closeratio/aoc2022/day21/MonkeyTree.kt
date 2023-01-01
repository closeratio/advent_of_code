package com.closeratio.aoc2022.day21

import com.closeratio.aoc2022.day21.Monkey.Id

class MonkeyTree(
    val map: Map<Id, Monkey>
) {

    fun computeRootNumber(): Long = map
        .getValue(Id("root"))
        .computeNumber(map)

}