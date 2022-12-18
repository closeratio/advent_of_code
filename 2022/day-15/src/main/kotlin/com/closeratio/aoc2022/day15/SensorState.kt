package com.closeratio.aoc2022.day15

import com.closeratio.aoc.common.math.Vec2
import kotlin.math.absoluteValue
import kotlin.math.max

data class SensorState(
    val sensors: Set<Sensor>,
    val beacons: Set<Beacon>
) {

    private fun findSensorRangesForYCoord(
        yCoord: Long
    ): List<LongRange> = sensors
        // Pair up the sensors with their corresponding beacon
        .map { sensor ->
            sensor to beacons.minBy { sensor.position.manhattanDistance(it.position) }
        }
        // Filter out any sensors for which the yCoord isn't within their "range"
        .filter { (sensor, beacon) ->
            val candidatePosition = Vec2(sensor.position.x, yCoord)
            candidatePosition.manhattanDistance(sensor.position) < sensor.position.manhattanDistance(beacon.position)
        }
        // Get all the X values which are in range of the sensor for the give yCoord
        .map { (sensor, beacon) ->
            val sensorPos = sensor.position
            val yDiff = (sensorPos.y - yCoord).absoluteValue
            val xLimit = sensorPos.manhattanDistance(beacon.position) - yDiff

            ((sensorPos.x - xLimit)..(sensorPos.x + xLimit))
        }
        // Consolidate ranges
        .sortedBy(LongRange::first)
        .fold(
            mutableListOf()
        ) { acc, curr ->
            val last = acc.lastOrNull()
            acc += if (last != null && last.last >= curr.first) {
                acc.removeLast()
                last.first..max(curr.last, last.last)
            } else {
                curr
            }
            acc
        }

    fun invalidBeaconPositions(
        yCoord: Long
    ): Int {
        val beaconAndSensorPositionCount = (sensors.map { it.position } + beacons.map { it.position })
            .filter { it.y == yCoord }
            .map(Vec2::x)
            .size

        val emptyPositions = findSensorRangesForYCoord(yCoord)
            .sumOf { range ->
                (range.last - range.first) + 1
            }

        return emptyPositions.toInt() - beaconAndSensorPositionCount
    }

}
