package com.closeratio.aoc2022.day21

class ConstantMonkey(id: Id, val number: Long): Monkey(id) {

    override fun computeNumber(monkeyMap: Map<Id, Monkey>): Long = number
}