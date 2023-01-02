package com.closeratio.aoc2022.day21

class ConstantMonkey(id: Id, val number: Long) : Monkey(id) {

    override fun computeNumber(monkeyMap: Map<Id, Monkey>): Long = number

    override fun computeRequiredHumanNumber(
        monkeyMap: Map<Id, Monkey>,
        routeToHuman: Set<Id>,
        requiredOutputNumber: Long
    ): Long {
        if (id !in routeToHuman) {
            throw IllegalStateException("Cannot compute required number of an edge monkey which isn't the human")
        }

        return requiredOutputNumber
    }

    override fun routeToMonkey(
        monkeyMap: Map<Id, Monkey>,
        id: Id
    ): Set<Id> = if (id == this.id) setOf(this.id) else emptySet()
}
