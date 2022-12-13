package com.closeratio.aoc2022.day13

class PacketPair(
    private val first: Packet,
    private val second: Packet
) {

    fun inOrder(): Boolean = first <= second

    override fun toString(): String {
        return "$first\n$second\n"
    }

}
