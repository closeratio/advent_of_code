package com.closeratio.aoc2022.day10

abstract class Instruction {

    abstract fun execute(state: MemoryState): MemoryState

}

