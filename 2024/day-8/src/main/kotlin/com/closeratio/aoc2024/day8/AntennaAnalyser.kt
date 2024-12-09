package com.closeratio.aoc2024.day8

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Service

@Service
class AntennaAnalyser {

    fun countAntinodes(
        lines: List<String>,
        includeResonantHarmonics: Boolean = false
    ): Long {
        val (antennas, width, height) = parseAntenna(lines)

        val frequencyGroups = antennas.groupBy(Antenna::frequency)

        val antinodes = frequencyGroups
            .flatMap { (_, antennas) ->
                antennas.flatMap { firstAntenna ->
                    antennas.filter { it != firstAntenna }.flatMap {
                        firstAntenna.antinodes(
                            it,
                            width,
                            height,
                            includeResonantHarmonics
                        )
                    }
                }
            }
            .toSet()

        return antinodes
            .size
            .toLong()
    }

    private fun parseAntenna(lines: List<String>): AntennaMap = lines
        .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                if (char != '.') {
                    Antenna(
                        Vec2(x, y),
                        char
                    )
                } else {
                    null
                }
            }
        }
        .let {
            AntennaMap(
                it.toSet(),
                lines.first().length.toLong(),
                lines.size.toLong()
            )
        }

}