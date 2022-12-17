package com.closeratio.aoc2022.day15

import com.closeratio.aoc.common.ResourceLoader
import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component

@Component
class SensorStateParser(
    private val resourceLoader: ResourceLoader
) {

    private fun parseCoord(line: String): Vec2 = line
        .split(",")
        .let { (xString, yString) ->
            val x = xString.split("=").last().toLong()
            val y = yString.split("=").last().toLong()
            Vec2(x, y)
        }

    private fun parseSensorAndBeacon(line: String): Pair<Sensor, Beacon> = line
        .split(": ")
        .let { (leftString, rightString) ->
            val sensorCoord = parseCoord(leftString.split(" ").takeLast(2).joinToString(""))
            val beaconCoord = parseCoord(rightString.split(" ").takeLast(2).joinToString(""))

            Sensor(sensorCoord) to Beacon(beaconCoord)
        }

    fun parseSensorState(path: String): SensorState = resourceLoader
        .loadResourceLines(path)
        .map(::parseSensorAndBeacon)
        .let { pairs ->
            SensorState(
                pairs.map(Pair<Sensor, Beacon>::first).toSet(),
                pairs.map(Pair<Sensor, Beacon>::second).toSet()
            )
        }

}
