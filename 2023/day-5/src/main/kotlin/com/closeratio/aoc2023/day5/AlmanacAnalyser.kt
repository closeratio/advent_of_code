package com.closeratio.aoc2023.day5

import org.springframework.stereotype.Component

@Component
class AlmanacAnalyser {

    private fun parseSeeds(line: String): List<Long> = line
        .split(":")[1]
        .trim()
        .split(" ")
        .map(String::trim)
        .map(String::toLong)

    private fun parseMapping(text: String): Mapping = text
        .split("\n")
        .drop(1)
        .map {
            val (targetStart, sourceStart, length) = it
                .split(" ")
                .map(String::toLong)

            Range(
                sourceStart..<(sourceStart + length),
                targetStart..<(targetStart + length)
            )
        }
        .let(::Mapping)

    fun computeLowestLocationNumber(
        input: String
    ): Long {
        val chunks = input.split("\n\n")
        val seeds = parseSeeds(chunks[0])
        val seedToSoilMap = parseMapping(chunks[1])
        val soilToFertilizerMap = parseMapping(chunks[2])
        val fertilizerToWaterMap = parseMapping(chunks[3])
        val waterToLightMap = parseMapping(chunks[4])
        val lightToTemperatureMap = parseMapping(chunks[5])
        val temperatureToHumidityMap = parseMapping(chunks[6])
        val humidityToLocationMap = parseMapping(chunks[7])

        return seeds
            .asSequence()
            .map(seedToSoilMap::mapInputValue)
            .map(soilToFertilizerMap::mapInputValue)
            .map(fertilizerToWaterMap::mapInputValue)
            .map(waterToLightMap::mapInputValue)
            .map(lightToTemperatureMap::mapInputValue)
            .map(temperatureToHumidityMap::mapInputValue)
            .map(humidityToLocationMap::mapInputValue)
            .toList()
            .min()
    }

}

