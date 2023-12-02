package com.closeratio.aoc2023.day2

data class Game(
    val id: Long,
    val handfulls: List<Handfull>
) {

    fun maxRed(): Long = handfulls.maxOf(Handfull::redCubes)
    fun maxGreen(): Long = handfulls.maxOf(Handfull::greenCubes)
    fun maxBlue(): Long = handfulls.maxOf(Handfull::blueCubes)

    fun powerSet(): Long = maxRed() * maxGreen() * maxBlue()

}
