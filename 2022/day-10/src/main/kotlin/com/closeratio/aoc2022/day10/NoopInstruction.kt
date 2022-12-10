package com.closeratio.aoc2022.day10

class NoopInstruction : Instruction() {

    override fun execute(state: MemoryState): MemoryState = MemoryState(
        state.xRegister,
        state.clockCycles + 1
    )

}
