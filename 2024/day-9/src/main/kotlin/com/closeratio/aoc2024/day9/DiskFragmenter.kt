package com.closeratio.aoc2024.day9

import org.springframework.stereotype.Service

@Service
class DiskFragmenter {

    fun fragmentAndCalculateChecksum(
        input: String
    ): Long = Memory.from(input).fragmentAndCalculateChecksum()

}