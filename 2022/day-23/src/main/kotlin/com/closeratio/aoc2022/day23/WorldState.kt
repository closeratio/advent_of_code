package com.closeratio.aoc2022.day23

import com.closeratio.aoc2022.day23.rules.Rule

data class WorldState(
    val elves: List<Elf>,
    val rules: List<Rule>
) {

    val elfMap = elves.associateBy(Elf::position)
    fun nextState(): WorldState {
        val movingElves = elves
            .map { elf ->
                val adjacent = elf.position.immediatelyAdjacent() + elf.position.diagonals()
                if (adjacent.all { it !in elfMap }) {
                    elf.position
                } else {
                    rules.firstNotNullOfOrNull { it.checkRule(elf, elfMap) } ?: elf.position
                } to elf
            }
            .groupBy({ it.first }) { it.second }
            .filterValues { it.size == 1 }
            .mapValues { (_, v) -> v.first() }

        val stationaryElves = (elves.toSet() - movingElves.values.toSet())
            .associateBy(Elf::position)

        val newElves = (movingElves + stationaryElves)
            .mapValues { (k, _) -> Elf(k) }
            .values
            .toList()

        return WorldState(
            newElves,
            rules.drop(1) + rules.first()
        )
    }

    fun computeEmptyGroundTiles(): Long {
        val minX = elves.minOf { it.position.x }
        val maxX = elves.maxOf { it.position.x } + 1
        val minY = elves.minOf { it.position.y }
        val maxY = elves.maxOf { it.position.y } + 1

        val area = (maxX - minX) * (maxY - minY)

        return area - elves.size
    }
}

