package com.closeratio.aoc2024.day9.memory

import java.util.*

class CohesiveMemory(
    val blocks: PriorityQueue<MemoryBlock>
) {

    fun reorganiseAndCalculateChecksum(): Long {
        val blocksToReorganise = blocks.reversed()

        blocksToReorganise.forEach { block ->
            val candidate = blocks
                .windowed(2)
                .firstOrNull { (first, second) ->
                    block.fitsBetween(first, second)
                }
                ?.first()

            if (candidate != null) {
                val movedBlock = block.moveTo(candidate.start + candidate.size)
                blocks.remove(block)
                blocks.add(movedBlock)
            }
        }

        return 0
    }

    companion object {
        fun from(input: String): CohesiveMemory {
            val blocks = mutableListOf<MemoryBlock>()
            var pointer = 0

            input.chunked(2).forEachIndexed { index, chunk ->
                val occupiedSize = chunk.first().toString().toInt()

                val start = pointer
                blocks += MemoryBlock(index.toLong(), start, occupiedSize)
                pointer += occupiedSize

                if (chunk.length == 2) {
                    pointer += chunk[1].toString().toInt()
                }
            }

            return CohesiveMemory(
                PriorityQueue<MemoryBlock>(blocks)
            )
        }
    }

}

