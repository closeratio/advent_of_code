package com.closeratio.aoc2022.day12

class HeightMap(
    private val tiles: List<Tile>
) {

    private class NoRouteFoundException(message: String) : RuntimeException(message)

    private val tileMap = tiles.associateBy(Tile::position)

    private fun findCandidates(tile: Tile): List<Tile> = tile
        .position
        .immediatelyAdjacent()
        .mapNotNull(tileMap::get)
        .filter { tile.isTraversable(it) }

    private fun computeRoute(
        start: Tile,
        end: Tile,
        routeMap: Map<Tile, Tile>
    ): List<Tile> {
        val reverseRoute = mutableListOf(end)
        while (reverseRoute.last() != start) {
            reverseRoute += routeMap.getValue(reverseRoute.last())
        }

        return reverseRoute.reversed()
    }

    fun computePathLength(
        start: Tile = tiles.first { it.isStart }
    ): Int {
        val end = tiles.first { it.isEnd }

        val visitedSet = linkedSetOf<Tile>()
        val candidateSet = linkedSetOf(start)
        val routeMap = hashMapOf<Tile, Tile>()

        var curr: Tile

        while (candidateSet.isNotEmpty()) {
            curr = candidateSet.first()
            candidateSet.remove(curr)

            if (curr == end) {
                return computeRoute(start, end, routeMap).size - 1
            }

            visitedSet += curr
            val candidates = findCandidates(curr)
                .filter { it !in visitedSet }
            candidates.forEach { routeMap[it] = curr }
            candidateSet += candidates
        }

        throw NoRouteFoundException("Unable to find path from $start to $end")
    }

    fun shortestRouteStartingFrom(
        heightString: String
    ): Int = tiles
        .filter { it.heightString == heightString }
        .mapNotNull {
            try {
                computePathLength(it)
            } catch (_: NoRouteFoundException) {
                null
            }
        }
        .min()

}
