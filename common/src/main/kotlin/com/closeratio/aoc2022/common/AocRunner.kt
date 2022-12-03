package com.closeratio.aoc2022.common

abstract class AocRunner {

    abstract fun getYear(): Int

    abstract fun getDay(): Int

    open fun part1Function(): (() -> Any)? = null

    open fun part2Function(): (() -> Any)? = null

}
