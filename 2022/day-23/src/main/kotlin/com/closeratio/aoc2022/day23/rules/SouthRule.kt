package com.closeratio.aoc2022.day23.rules

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day23.Elf

class SouthRule : Rule() {

    override fun checkRule(elf: Elf, elfMap: Map<Vec2, Elf>): Vec2? = if (listOf(
            elf.position.down() !in elfMap,
            elf.position.down().left() !in elfMap,
            elf.position.down().right() !in elfMap
        ).all { it }
    ) elf.position.down() else null

}