package com.closeratio.aoc2023.day9

class SequenceAnalyser(
    val numberSequences: List<NumberSequence>
) {

    fun sumNextValues(): Long = numberSequences
        .map(NumberSequence::getNextValue)
        .sum()

    fun sumPreviousValues(): Long = numberSequences
        .map(NumberSequence::getPreviousValue)
        .sum()

}