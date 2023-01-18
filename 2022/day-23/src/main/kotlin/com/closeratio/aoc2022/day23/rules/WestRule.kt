package com.closeratio.aoc2022.day23.rules

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.Elf

class WestRule : Rule() {

    override fun checkRule(elf: Elf, elfMap: Map<Vec2, Elf>): Vec2? = if (listOf(
            elf.position.left() !in elfMap,
            elf.position.left().up() !in elfMap,
            elf.position.left().down() !in elfMap
        ).all { it }
    ) elf.position.left() else null

}

