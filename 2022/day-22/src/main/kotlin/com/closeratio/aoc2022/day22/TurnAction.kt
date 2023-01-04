package com.closeratio.aoc2022.day22

class TurnAction(
    val direction: Direction
) : Action() {

    enum class Direction {
        LEFT,
        RIGHT
    }

}
