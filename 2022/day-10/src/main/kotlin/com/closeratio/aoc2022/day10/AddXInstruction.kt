package com.closeratio.aoc2022.day10

class AddXInstruction(
    val value: Long
) : Instruction() {

    override fun execute(state: MemoryState): MemoryState = MemoryState(
        state.xRegister + value,
        state.clockCycles + 2
    )

}
