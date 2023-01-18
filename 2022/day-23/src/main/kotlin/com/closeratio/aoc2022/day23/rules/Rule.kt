package com.closeratio.aoc2022.day23.rules

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.Elf

abstract class Rule {

    abstract fun checkRule(elf: Elf, elfMap: Map<Vec2, Elf>): Vec2?

}

