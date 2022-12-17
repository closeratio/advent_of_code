package com.closeratio.aoc2022.day15

import com.closeratio.aoc.common.math.Vec2

data class SensorState(
    val sensors: Set<Sensor>,
    val beacons: Set<Beacon>
) {

    fun invalidBeaconPositions(
        yCoord: Long
    ): Int {
        val beaconAndSensorPositions = sensors.map { it.position }.toSet() + beacons.map { it.position }

        val emptyPositions = sensors
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
            .flatMap { (sensor, beacon) ->
                val sensorPos = sensor.position
                val distance = sensorPos.manhattanDistance(beacon.position)

                ((sensorPos.x - distance)..(sensorPos.x + distance)).map { x ->
                    Vec2(x, yCoord)
                }.filter { it.manhattanDistance(sensorPos) <= distance }
            }
            // Consolidate them and filter out any positions which are already taken by sensors or beacons
            .toSet()
            .filter { it !in beaconAndSensorPositions }

        return emptyPositions.size
    }

}
