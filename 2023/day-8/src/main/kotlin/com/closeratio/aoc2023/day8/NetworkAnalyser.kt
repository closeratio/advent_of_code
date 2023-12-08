package com.closeratio.aoc2023.day8

import com.closeratio.aoc2023.day8.Direction.LEFT
import com.closeratio.aoc2023.day8.Direction.RIGHT

class NetworkAnalyser(
    val network: Map<NodeId, Node>,
    val instructions: List<Direction>
) {

    fun stepCount(): Long {
        var stepCount = 0L
        var current = network.getValue(NodeId("AAA"))
        var instructionIndex = 0
        while (current.id != NodeId("ZZZ")) {
            val instruction = instructions[instructionIndex]

            current = when (instruction) {
                LEFT -> current.leftNode
                RIGHT -> current.rightNode
            }

            stepCount++
            instructionIndex = (instructionIndex + 1) % instructions.size
        }

        return stepCount
    }

}

