package com.closeratio.aoc2023.day5

class Almanac(
    val seedRanges: List<LongRange>,
    val seedToSoilMap: Mapping,
    val soilToFertilizerMap: Mapping,
    val fertilizerToWaterMap: Mapping,
    val waterToLightMap: Mapping,
    val lightToTemperatureMap: Mapping,
    val temperatureToHumidityMap: Mapping,
    val humidityToLocationMap: Mapping
) {

    fun computeLowestLocationNumber(): Long {
        var found = false
        var curr: Long = 0
        while (!found) {
            val result = reverseSearch(curr)
            found = seedRanges.any { result in it }

            curr++
        }

        return curr - 1 // - 1 because curr is always incremented even if the value was found
    }

    fun reverseSearch(locationNumber: Long): Long = locationNumber
        .let(humidityToLocationMap::mapOutputValue)
        .let(temperatureToHumidityMap::mapOutputValue)
        .let(lightToTemperatureMap::mapOutputValue)
        .let(waterToLightMap::mapOutputValue)
        .let(fertilizerToWaterMap::mapOutputValue)
        .let(soilToFertilizerMap::mapOutputValue)
        .let(seedToSoilMap::mapOutputValue)

}

