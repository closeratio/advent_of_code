package com.aoc2017.day18_part1.instructions

import com.aoc2017.day18_part1.Register
import com.aoc2017.day18_part1.ValueHolder

class MultiplyInstruction(
        val reg: Register,
        val value: ValueHolder)
    : Instruction() {

    override fun equals(other: Any?): Boolean {
        val inst = other as? MultiplyInstruction ?: return false
        return reg == inst.reg && value == inst.value
    }

    override fun hashCode(): Int {
        return reg.hashCode() * value.hashCode()
    }

    override fun toString(): String {
        return "Multiply instruction ($reg, $value)"
    }

    override fun getSourceRep(): String {
        return "mul ${reg.getSourceRep()} ${value.getSourceRep()}"
    }
}