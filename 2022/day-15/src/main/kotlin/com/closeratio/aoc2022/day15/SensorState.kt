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
            .flatMap { sensor ->
                val sensorPos = sensor.position
                val beacon = beacons.minBy { sensorPos.manhattanDistance(it.position) }
                val distance = sensorPos.manhattanDistance(beacon.position)

                ((sensorPos.y - distance)..(sensorPos.y + distance))
                    .flatMap { y ->
                        ((sensorPos.x - distance)..(sensorPos.x + distance)).map { x ->
                            Vec2(x, y)
                        }
                    }
                    .filter { it.manhattanDistance(sensorPos) <= distance }
            }
            .toSet()
            .filter { it !in beaconAndSensorPositions }
            .filter { it.y == yCoord }

        return emptyPositions.size
    }

}
