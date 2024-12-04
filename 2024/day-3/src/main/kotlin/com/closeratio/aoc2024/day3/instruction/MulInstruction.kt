package com.closeratio.aoc2024.day3.instruction

class MulInstruction(
    indexRange: IntRange,
    val leftValue: Long,
    val rightValue: Long
) : Instruction(indexRange) {
    val product = leftValue * rightValue
}

