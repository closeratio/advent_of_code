package com.closeratio.aoc2022.day13

class Packet(
    private val root: InnerListItem
) : Comparable<Packet> {

    override fun compareTo(other: Packet): Int = root.compareTo(other.root)
    override fun toString(): String {
        return root.toOriginalString()
    }


}

