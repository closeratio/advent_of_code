package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day22.Orientation.*

class SimpleMonkeyMap(
    private val map: Map<Vec2, Tile>,
    private val actions: List<Action>
) : MonkeyMap() {

    data class AdjacentTiles(
        val up: Tile,
        val down: Tile,
        val left: Tile,
        val right: Tile
    )

    private val adjacencyMap: Map<Vec2, AdjacentTiles> = map
        .values
        .associate { tile ->
            val pos = tile.position

            pos to AdjacentTiles(
                map[pos.up()] ?: map.values.filter { it.position.x == pos.x }.maxBy { it.position.y },
                map[pos.down()] ?: map.values.filter { it.position.x == pos.x }.minBy { it.position.y },
                map[pos.left()] ?: map.values.filter { it.position.y == pos.y }.maxBy { it.position.x },
                map[pos.right()] ?: map.values.filter { it.position.y == pos.y }.minBy { it.position.x }
            )
        }

    override fun computePassword(): Long {
        var currPos = map.values.filter { it.position.y == 0L }.minBy { it.position.x }.position
        var currOrientation = EAST

        actions.forEach { action ->
            when (action) {
                is TurnAction -> currOrientation = currOrientation.turn(action.direction)
                is MoveAction -> {
                    repeat(action.amount.toInt()) {
                        val adjacent = adjacencyMap.getValue(currPos)
                        val nextTile = when (currOrientation) {
                            NORTH -> adjacent.up
                            SOUTH -> adjacent.down
                            EAST -> adjacent.right
                            WEST -> adjacent.left
                        }

                        if (nextTile !is Wall) {
                            currPos = nextTile.position
                        }
                    }
                }
            }
        }

        return ((currPos.y + 1) * 1000) + ((currPos.x + 1) * 4) + when (currOrientation) {
            NORTH -> 3
            SOUTH -> 1
            EAST -> 0
            WEST -> 2
        }
    }

}
