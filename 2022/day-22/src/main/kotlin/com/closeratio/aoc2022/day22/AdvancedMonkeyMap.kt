package com.closeratio.aoc2022.day22

import com.closeratio.aoc.common.math.Vec2
import com.closeratio.aoc2022.day22.Orientation.*

class AdvancedMonkeyMap(
    private val map: Map<Vec2, Tile>,
    private val actions: List<Action>
) : MonkeyMap() {

    data class Zone(
        val id: String,
        val tiles: Set<Tile>
    )

    data class TransitionRuleSet(
        val north: ZoneTransitionRule,
        val east: ZoneTransitionRule,
        val south: ZoneTransitionRule,
        val west: ZoneTransitionRule
    )

    data class ZoneTransitionRule(
        val zone: Zone,
        val entrySide: Orientation,
        val flipEntryIndex: Boolean
    )

    private val zones = listOf(
        Zone("A", map.values.filter { it.position.x >= 100 }.toSet()),
        Zone("B", map.values.filter { it.position.y < 50 }.filter { it.position.x < 100 }.toSet()),
        Zone("C", map.values.filter { it.position.y in 50..99 }.toSet()),
        Zone("D", map.values.filter { it.position.y >= 100 }.filter { it.position.x >= 50 }.toSet()),
        Zone("E", map.values.filter { it.position.y in 100..149 }.filter { it.position.x < 50 }.toSet()),
        Zone("F", map.values.filter { it.position.y >= 150 }.filter { it.position.x < 50 }.toSet())
    )

    private val zoneMap = zones.associateBy(Zone::id)

    private val adjacentZoneMap: Map<Zone, TransitionRuleSet> = mapOf(
        zoneMap.getValue("A") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("F"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("D"), EAST, true),
            ZoneTransitionRule(zoneMap.getValue("C"), EAST, false),
            ZoneTransitionRule(zoneMap.getValue("B"), EAST, false),
        ),
        zoneMap.getValue("B") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("F"), WEST, false),
            ZoneTransitionRule(zoneMap.getValue("A"), WEST, false),
            ZoneTransitionRule(zoneMap.getValue("C"), NORTH, false),
            ZoneTransitionRule(zoneMap.getValue("E"), WEST, true),
        ),
        zoneMap.getValue("C") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("B"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("A"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("D"), NORTH, false),
            ZoneTransitionRule(zoneMap.getValue("E"), NORTH, false),
        ),
        zoneMap.getValue("D") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("C"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("A"), EAST, true),
            ZoneTransitionRule(zoneMap.getValue("F"), EAST, false),
            ZoneTransitionRule(zoneMap.getValue("E"), EAST, false),
        ),
        zoneMap.getValue("E") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("C"), WEST, false),
            ZoneTransitionRule(zoneMap.getValue("D"), WEST, false),
            ZoneTransitionRule(zoneMap.getValue("F"), NORTH, false),
            ZoneTransitionRule(zoneMap.getValue("B"), WEST, true),
        ),
        zoneMap.getValue("F") to TransitionRuleSet(
            ZoneTransitionRule(zoneMap.getValue("E"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("D"), SOUTH, false),
            ZoneTransitionRule(zoneMap.getValue("A"), NORTH, false),
            ZoneTransitionRule(zoneMap.getValue("B"), NORTH, false),
        )
    )

    private val zoneWidth =
        (zones.first().tiles.let { riles -> riles.maxOf { it.position.x } - riles.minOf { it.position.x } }) + 1

    override fun computePassword(): Long {
        var currPos = map.values.filter { it.position.y == 0L }.minBy { it.position.x }
        var currOrientation = EAST

        actions.forEach { action ->
            when (action) {
                is TurnAction -> currOrientation = currOrientation.turn(action.direction)
                is MoveAction -> {
                    repeat(action.amount.toInt()) {
                        val (nextTile, nextOrientation) = move(currPos, currOrientation)

                        if (nextTile !is Wall) {
                            currPos = nextTile
                            currOrientation = nextOrientation
                        }
                    }
                }
            }
        }

        val (x, y) = currPos.position

        return ((y + 1) * 1000) + ((x + 1) * 4) + when (currOrientation) {
            NORTH -> 3
            SOUTH -> 1
            EAST -> 0
            WEST -> 2
        }
    }

    private fun move(
        currTile: Tile,
        currOrientation: Orientation
    ): Pair<Tile, Orientation> {
        val nextTile = when (currOrientation) {
            NORTH -> map[currTile.position.up()]
            SOUTH -> map[currTile.position.down()]
            EAST -> map[currTile.position.right()]
            WEST -> map[currTile.position.left()]
        }

        // If it's adjacent without having to go "over the edge" then it's a simple case
        if (nextTile != null) {
            return nextTile to currOrientation
        }

        // Otherwise find the transition rules for the next zone
        val currZone = zones.find { currTile in it.tiles }!!
        val (nextZone, entrySide, flipEntryIndex) = adjacentZoneMap
            .getValue(currZone)
            .let {
                when (currOrientation) {
                    NORTH -> it.north
                    EAST -> it.east
                    SOUTH -> it.south
                    WEST -> it.west
                }
            }


        // Get the list of tiles along the side of the zone being entered
        val minX = nextZone.tiles.minOf { it.position.x }
        val maxX = nextZone.tiles.maxOf { it.position.x }
        val minY = nextZone.tiles.minOf { it.position.y }
        val maxY = nextZone.tiles.maxOf { it.position.y }

        val tileList = when (entrySide) {
            NORTH -> nextZone.tiles.filter { it.position.y == minY }.sortedBy { it.position.x }
            SOUTH -> nextZone.tiles.filter { it.position.y == maxY }.sortedBy { it.position.x }
            EAST -> nextZone.tiles.filter { it.position.x == maxX }.sortedBy { it.position.y }
            WEST -> nextZone.tiles.filter { it.position.x == minX }.sortedBy { it.position.y }
        }

        // Work out how far from the top left corner of the zone we will be
        val currZoneOrigin = currZone.tiles.sortedBy { it.position.x }.minBy { it.position.y }.position
        val currOffset = when (currOrientation) {
            NORTH, SOUTH -> currTile.position.x - currZoneOrigin.x
            EAST, WEST -> currTile.position.y - currZoneOrigin.y
        }

        // Work out the new position and orientation
        val index = if (!flipEntryIndex) currOffset else (zoneWidth - 1) - currOffset
        val newTile = tileList[index.toInt()]
        val newOrientation = when (entrySide) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            EAST -> WEST
            WEST -> EAST
        }

        return newTile to newOrientation
    }

}
