package com.closeratio.aoc2022.day5

data class CargoState(
    val stackMap: Map<Int, Stack>
) {

    fun executeInstruction(
        instruction: MoveInstruction,
        moveMultipleCrates: Boolean = false
    ): CargoState {

        val sourceStack = stackMap
            .getValue(instruction.source)
            .crates

        val newMap = stackMap.toMutableMap()
        newMap[instruction.source] = Stack(sourceStack.drop(instruction.count))

        val movedStack = sourceStack
            .take(instruction.count)
            .let {
                if (moveMultipleCrates) it else it.reversed()
            }

        newMap[instruction.target] = Stack(movedStack + stackMap.getValue(instruction.target).crates)

        return CargoState(newMap)
    }

    fun topLineString(): String = stackMap
        .keys
        .sorted()
        .map(stackMap::getValue)
        .joinToString("") { it.crates.firstOrNull()?.id ?: " " }

}

