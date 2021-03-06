package com.aoc2017.day18_part2

class Register(
        val name: String,
        var value: Long = 0L
): ValueHolder() {

    override fun getVal(): Long {
        return value
    }

    override fun equals(other: Any?): Boolean {
        val reg = other as? Register ?: return false
        return name == reg.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Register $name ($value)"
    }

    override fun getSourceRep(): String {
        return name
    }
}