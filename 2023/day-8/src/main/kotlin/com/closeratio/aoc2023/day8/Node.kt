package com.closeratio.aoc2023.day8

class Node(
    val id: NodeId
) {
    lateinit var leftNode: Node
    lateinit var rightNode: Node
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
