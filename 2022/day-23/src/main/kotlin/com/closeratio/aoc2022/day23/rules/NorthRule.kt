package com.closeratio.aoc2022.day23.rules

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.Elf

class NorthRule : Rule() {

    override fun checkRule(elf: Elf, elfMap: Map<Vec2, Elf>): Vec2? = if (listOf(
            elf.position.up() !in elfMap,
            elf.position.up().right() !in elfMap,
            elf.position.up().left() !in elfMap
        ).all { it }
    ) elf.position.up() else null

}