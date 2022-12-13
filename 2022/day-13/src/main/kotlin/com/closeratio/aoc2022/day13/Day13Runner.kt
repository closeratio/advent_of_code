package com.closeratio.aoc2022.day13

import com.closeratio.aoc.common.Aoc2022Runner
import org.springframework.stereotype.Component

@Component
class Day13Runner(
    private val packetPairParser: PacketPairParser
) : Aoc2022Runner() {

    override fun getDay(): Int = 13

    override fun part1Function(): () -> Int = {
        packetPairParser.sumCorrectOrderPairs("/2022_day_13_input.txt")
    }

    override fun part2Function(): () -> Int = {
        packetPairParser.computeDecoderKey("/2022_day_13_input.txt")
    }

}
