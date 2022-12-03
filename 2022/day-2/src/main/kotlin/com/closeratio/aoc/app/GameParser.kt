package com.closeratio.aoc.app

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class GameParser(
    val resourceLoader: ResourceLoader
) {

    fun computePlayPairPoints(path: String): Long = resourceLoader
        .loadResourceText(path)
        .split("\n")
        .map(RockPaperScissorEnum.Companion::decodePlayPair)
        .sumOf { (first, second) -> second.computePoints(first) }

    fun computePlayResultPairPoints(path: String): Long = resourceLoader
        .loadResourceText(path)
        .split("\n")
        .map { line -> RockPaperScissorEnum.decodeChar(line.first()) to GameResult.decodeChar(line.last()) }
        .sumOf { (firstPlay, desiredResult) -> firstPlay.computePoints(desiredResult) }

}
