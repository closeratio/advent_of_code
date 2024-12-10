package com.closeratio.aoc2024.day9

class Memory(
    val values: MutableList<Long?>
) {
    val maxValue = values.maxBy { it ?: -1 }

    var nextFreeIndex = values.indexOf(null)
    var lastOccupiedIndex = values.lastIndexOf(maxValue)

    fun fragmentAndCalculateChecksum(): Long {
        while (nextFreeIndex < lastOccupiedIndex) {
            values[nextFreeIndex] = values[lastOccupiedIndex]
            values[lastOccupiedIndex] = null

            updateNextFreeIndex()
            updateLastOccupiedIndex()
        }

        return values
            .mapIndexed { index, value -> index * (value ?: 0) }
            .sum()
    }

    private fun updateNextFreeIndex() {
        while (values[nextFreeIndex] != null) {
            nextFreeIndex++
        }
    }

    private fun updateLastOccupiedIndex() {
        while (values[lastOccupiedIndex] == null) {
            lastOccupiedIndex--
        }
    }

    companion object {
        fun from(input: String): Memory {
            val memory = mutableListOf<Long?>()

            input.chunked(2).forEachIndexed { index, chunk ->
                val occupiedSize = chunk.first().toString().toInt()
                (0..<occupiedSize).forEach {
                    memory += index.toLong()
                }

                if (chunk.length == 2) {
                    val emptySize = chunk[1].toString().toInt()
                    (0..<emptySize).forEach {
                        memory += null
                    }
                }
            }

            return Memory(memory)
        }
    }

}