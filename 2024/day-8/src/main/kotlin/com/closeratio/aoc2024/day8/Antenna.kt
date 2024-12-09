package com.closeratio.aoc2024.day8

import com.closeratio.aoc.common.math.Vec2

data class Antenna(
    val position: Vec2,
    val frequency: Char
) {

    fun antinodes(
        other: Antenna,
        width: Long,
        height: Long,
        includeResonantHarmonics: Boolean
    ): Set<Vec2> {
        if (other.frequency != frequency) {
            throw IllegalArgumentException("Antenna $frequency is not the same as the other $other.")
        }

        val delta = position - other.position

        if (!includeResonantHarmonics) {
            return listOf(
                position + delta,
                other.position - delta
            ).filter { it.x in 0..<width && it.y in 0..<height }.toSet()
        }

        val antinodes = mutableListOf(position)
        while (antinodes.last().x in 0..<width && antinodes.last().y in 0..<height) {
            antinodes += antinodes.last() + delta
        }

        antinodes += position - delta
        while (antinodes.last().x in 0..<width && antinodes.last().y in 0..<height) {
            antinodes += antinodes.last() - delta
        }

        return antinodes
            .filter { it.x in 0..<width && it.y in 0..<height }
            .toSet()
    }

}
