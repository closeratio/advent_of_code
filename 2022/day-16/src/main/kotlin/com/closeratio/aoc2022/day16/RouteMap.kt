package com.closeratio.aoc2022.day16

import java.util.*

class RouteMap(
    private val routes: Map<String, Map<String, Int>>
) {

    companion object {

        private fun calculateRouteLength(
            valveMap: Map<String, Valve>,
            start: Valve,
            finish: Valve
        ): Int {
            val routeMap = mutableMapOf<String, String>()
            val visited = mutableSetOf<String>()
            val candidates = LinkedList(listOf(start.id))

            while (candidates.isNotEmpty()) {
                val current = candidates.pop()
                visited += current

                if (current == finish.id) {
                    val reversedRoute = LinkedList(listOf(current))

                    while (reversedRoute.last() != start.id) {
                        reversedRoute.add(routeMap.getValue(reversedRoute.last()))
                    }

                    return reversedRoute.reversed().size - 1
                }

                val adjacent = valveMap
                    .getValue(current)
                    .connectedValveIds
                    .filter { it !in visited }
                adjacent.forEach { routeMap[it] = current }
                candidates += adjacent
            }

            throw IllegalStateException("Unable to find route from $start to $finish")
        }

        fun from(valveMap: Map<String, Valve>): RouteMap = RouteMap(valveMap
            .values
            .associate { start ->
                start.id to valveMap.values
                    .filter { it.id != start.id }
                    .associate { finish ->
                        finish.id to calculateRouteLength(valveMap, start, finish)
                    }
            })
    }

    fun routeLength(from: String, to: String): Int = routes
        .getValue(from)
        .getValue(to)

}
