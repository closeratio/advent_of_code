package com.closeratio.aoc2023.day9

class SequenceAnalyser(
    val numberSequences: List<NumberSequence>
) {

    fun sumExtrapolatedValues(): Long = numberSequences
        .map(NumberSequence::getNextValue)
        .sum()

}