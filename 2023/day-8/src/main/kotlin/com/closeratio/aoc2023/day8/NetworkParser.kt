package com.closeratio.aoc2023.day8

import com.closeratio.aoc2023.day8.Direction.LEFT
import com.closeratio.aoc2023.day8.Direction.RIGHT
import org.springframework.stereotype.Component

@Component
class NetworkParser {

    fun parse(
        input: List<String>
    ): NetworkAnalyser {
        val instructions = input
            .first()
            .map { c ->
                when (c) {
                    'L' -> LEFT
                    'R' -> RIGHT
                    else -> throw IllegalArgumentException("Invalid direction: $c")
                }
            }

        val network = mutableMapOf<NodeId, Node>()
        input
            .drop(2)
            .map { line ->
                val (id, leftRightString) = line.split(" = ")
                val (left, right) = """(\w+), (\w+)"""
                    .toRegex()
                    .find(leftRightString)!!
                    .groupValues
                    .drop(1)

                listOf(
                    id,
                    left,
                    right
                ).map(::NodeId)
            }
            .forEach { (nodeId, leftId, rightId) ->
                val node = network.getOrPut(nodeId) { Node(nodeId) }
                node.leftNode = network.getOrPut(leftId) { Node(leftId) }
                node.rightNode = network.getOrPut(rightId) { Node(rightId) }
            }

        return NetworkAnalyser(
            network,
            instructions
        )
    }

}
