package com.closeratio.aoc2024.day3.instruction

abstract class Instruction(
    val indexRange: IntRange
) : Comparable<Instruction> {
    override fun compareTo(other: Instruction): Int = indexRange.first.compareTo(other.indexRange.first)
}