package com.closeratio.aoc2022.day23.rules

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.Elf

class EastRule : Rule() {

    override fun checkRule(elf: Elf, elfMap: Map<Vec2, Elf>): Vec2? = if (listOf(
            elf.position.right() !in elfMap,
            elf.position.right().up() !in elfMap,
            elf.position.right().down() !in elfMap
        ).all { it }
    ) elf.position.right() else null

}