package com.closeratio.aoc2022.day10

class Computer(
    val instructions: List<Instruction>
) {

    companion object {
        private const val screenWidth = 40
    }

    fun computeSignalStrengthSum(
        keyCycles: Set<Long> = setOf(20, 60, 100, 140, 180, 220)
    ): Long {
        val memoryStates = instructions.fold(
            listOf(MemoryState(1, 1))
        ) { acc, curr ->
            acc + curr.execute(acc.last())
        }

        return memoryStates
            .windowed(2) { (first, second) ->
                val includedCycles = (first.clockCycles until second.clockCycles).toSet()
                val intersect = includedCycles.intersect(keyCycles)
                if (intersect.isNotEmpty()) {
                    first.xRegister * intersect.first()
                } else {
                    null
                }
            }
            .filterNotNull()
            .sum()
    }

    fun generateCrtString(): String {
        val memoryStates = instructions.fold(
            listOf(MemoryState(1, 1))
        ) { acc, curr ->
            acc + curr.execute(acc.last())
        }

        val spriteMap: Map<Long, Set<Long>> = memoryStates
            .windowed(2)
            .flatMap { (first, second) ->
                (first.clockCycles until second.clockCycles)
                    .toList()
                    .map { clockCycle ->
                        val spriteStart = first.xRegister - 1
                        val spriteEnd = first.xRegister + 1
                        clockCycle to (spriteStart..spriteEnd).toSet()
                    }
            }
            .toMap()

        val rawString = (1L..240L)
            .joinToString("") { cycle ->
                val spriteXValues = spriteMap.getValue(cycle)
                val currentX = (cycle - 1) % screenWidth
                if (currentX in spriteXValues) {
                    "#"
                } else {
                    "."
                }
            }

        return rawString
            .chunked(screenWidth)
            .joinToString("\n")
    }

}

