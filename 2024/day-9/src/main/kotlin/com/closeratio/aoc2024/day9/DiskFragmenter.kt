package com.closeratio.aoc2024.day9

import com.closeratio.aoc2024.day9.memory.CohesiveMemory
import com.closeratio.aoc2024.day9.memory.FragmentedMemory
import org.springframework.stereotype.Service

@Service
class DiskFragmenter {

    fun fragmentAndCalculateChecksum(
        input: String
    ): Long = FragmentedMemory.from(input).fragmentAndCalculateChecksum()

    fun defragmentAndCalculateChecksum(
        input: String
    ): Long = CohesiveMemory.from(input).reorganiseAndCalculateChecksum()

}