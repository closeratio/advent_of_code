package com.closeratio.aoc2022.day6

data class Datastream(
    val data: String
) {

    fun computePacketIndex(): Int = data
        .windowed(size = 4)
        .indexOfFirst { chunk ->
            chunk.toSet().size == 4
        } + 4 // Add 4 because index is start of window and solution is +1 offset from index

    fun computeMessageIndex(): Int = data
        .windowed(size = 14)
        .indexOfFirst { chunk ->
            chunk.toSet().size == 14
        } + 14


}
