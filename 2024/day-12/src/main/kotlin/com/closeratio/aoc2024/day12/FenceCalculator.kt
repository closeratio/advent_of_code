package com.closeratio.aoc2024.day12

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Service
import java.util.*

@Service
class FenceCalculator {

    fun calculateFencePrice(
        input: List<String>
    ): Long {
        val regions = input
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Plot(
                        Vec2(x, y),
                        char.toString()
                    )
                }
            }
            .groupBy(Plot::type)
            .flatMap { calculateRegions(it.value) }

        return regions.sumOf(Region::calculateFencePrice)
    }

    private fun calculateRegions(
        plotList: List<Plot>
    ): List<Region> {
        val plotMap = plotList.associateBy(Plot::pos)

        val unused = plotList.map(Plot::pos).toMutableSet()
        val regions = mutableListOf<Region>()
        while (unused.isNotEmpty()) {
            val queue = LinkedList<Vec2>().also {
                it.add(unused.first())
            }

            val plots = mutableSetOf<Vec2>()
            while (queue.isNotEmpty()) {
                val curr = queue.removeFirst()
                plots += curr
                queue += curr.immediatelyAdjacent()
                    .filter { it in plotMap }
                    .filter { it !in plots }
                    .filter { it !in queue }
            }

            unused.removeAll(plots)
            regions.add(
                Region(
                    plots.map(plotMap::getValue).toSet()
                )
            )
        }

        return regions
    }

}