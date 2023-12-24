package com.closeratio.aoc2023.day18

import com.closeratio.aoc.common.math.Vec2
import org.springframework.stereotype.Component
import java.util.*

@Component
class DigAnalyser(
    private val digSiteParser: DigSiteParser
) {

    fun computeDigArea(
        lines: List<String>,
        useHexInstructions: Boolean = false
    ): Long {
        val digSite = digSiteParser.parseDigSite(lines, useHexInstructions)

        val knownInternalTiles = mutableSetOf<Vec2>()
        val knownExternalTiles = mutableSetOf<Vec2>()

        digSite.yRange.forEach { y ->
            digSite.xRange.forEach { x ->
                checkTile(
                    Vec2(x, y),
                    digSite,
                    knownInternalTiles,
                    knownExternalTiles
                )
            }
        }

        return (knownInternalTiles.size + digSite.trenchTiles.size).toLong()
    }

    private fun checkTile(
        startPos: Vec2,
        digSite: DigSite,
        knownInternalTiles: MutableSet<Vec2>,
        knownExternalTiles: MutableSet<Vec2>
    ) {
        if (startPos in knownInternalTiles || startPos in knownExternalTiles || startPos in digSite.trenchTiles) {
            return
        }

        val explored = mutableSetOf<Vec2>()
        val toExploreSet = mutableSetOf(startPos)
        val toExploreList = LinkedList(listOf(startPos))
        while (toExploreList.isNotEmpty()) {
            val pos = toExploreList.poll()
            toExploreSet -= pos
            explored += pos

            if (pos.x !in digSite.xRange || pos.y !in digSite.yRange) {
                knownExternalTiles += explored
                return
            }

            val nextPositions = pos.immediatelyAdjacent()
                .filter { it !in explored }
                .filter { it !in toExploreSet }
                .filter { it !in digSite.trenchTiles }

            toExploreSet += nextPositions
            toExploreList += nextPositions
        }

        knownInternalTiles += explored
    }

}