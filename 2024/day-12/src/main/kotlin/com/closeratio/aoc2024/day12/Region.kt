package com.closeratio.aoc2024.day12

data class Region(
    val plots: Set<Plot>
) {
    private val posSet = plots.map(Plot::pos).toSet()

    fun calculateFencePrice(): Long = plots.sumOf { it.calculateRequiredFence(posSet) } * plots.size.toLong()

}
