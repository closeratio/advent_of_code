package com.closeratio.aoc2024.day9.memory

data class MemoryBlock(
    val id: Long,
    val start: Int,
    val size: Int
) : Comparable<MemoryBlock> {

    fun fitsBetween(
        firstBlock: MemoryBlock,
        secondBlock: MemoryBlock
    ): Boolean {
        val newStart = firstBlock.start + firstBlock.size
        val endIndex = newStart + size - 1
        return endIndex < secondBlock.start
    }

    fun moveTo(
        newStart: Int
    ): MemoryBlock = copy(
        start = newStart
    )

    fun checksum(): Long = (start..<(start + size)).sumOf { it * id }

    override fun compareTo(other: MemoryBlock): Int = start.compareTo(other.start)
}