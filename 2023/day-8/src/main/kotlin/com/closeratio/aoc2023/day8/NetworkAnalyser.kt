package com.closeratio.aoc2023.day8

import com.closeratio.aoc.common.math.lcm
import com.closeratio.aoc2023.day8.Direction.LEFT
import com.closeratio.aoc2023.day8.Direction.RIGHT
import org.slf4j.LoggerFactory

class NetworkAnalyser(
    val network: Map<NodeId, Node>,
    val instructions: List<Direction>
) {

    private val log = LoggerFactory.getLogger(javaClass)

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

    fun stepCountSimultaneous(): Long {
        var stepCount = 0L
        val currentNodes = network
            .keys
            .filter { it.value.endsWith("A") }
            .map(network::getValue)
            .toMutableList()

        var instructionIndex = 0
        val cycleCountMap = mutableMapOf<NodeId, Long>()
        while (!isFinished(currentNodes)) {
            val instruction = instructions[instructionIndex]

            currentNodes
                .forEachIndexed { index, node ->
                    if (!node.isEndNode) {
                        currentNodes[index] = when (instruction) {
                            LEFT -> node.leftNode
                            RIGHT -> node.rightNode
                        }
                        if (currentNodes[index].isEndNode) {
                            cycleCountMap[node.id] = stepCount + 1
                        }
                    }
                }

            stepCount++
            instructionIndex = (instructionIndex + 1) % instructions.size
        }

        return cycleCountMap.values.lcm()
    }

    private fun isFinished(currentNodes: List<Node>): Boolean = currentNodes.all(Node::isEndNode)

}

