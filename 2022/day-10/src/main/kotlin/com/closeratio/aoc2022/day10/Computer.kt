package com.closeratio.aoc2022.day10

class Computer(
    val instructions: List<Instruction>
) {

    fun computeSignalStrengthSum(
        keyCycles: Set<Long> = setOf(20, 60, 100, 140, 180, 220)
    ): Long {
        val memoryStates = instructions.fold(
            listOf(MemoryState(1, 1))
        ) { acc, curr ->
            acc + curr.execute(acc.last())
        }

        return memoryStates
            .windowed(2) { (first, second) ->
                val includedCycles = (first.clockCycles until second.clockCycles).toSet()
                val intersect = includedCycles.intersect(keyCycles)
                if (intersect.isNotEmpty()) {
                    first.xRegister * intersect.first()
                } else {
                    null
                }
            }
            .filterNotNull()
            .sum()
    }

}

