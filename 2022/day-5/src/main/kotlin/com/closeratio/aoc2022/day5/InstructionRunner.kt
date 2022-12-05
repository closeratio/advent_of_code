package com.closeratio.aoc2022.day5

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class InstructionRunner(
    private val resourceLoader: ResourceLoader
) {

    fun loadState(path: String): Pair<CargoState, List<MoveInstruction>> {
        val lines = resourceLoader.loadResourceLines(path, false)
        val emptyLineIndex = lines.indexOfFirst(String::isEmpty)

        val cargoStateLines = lines.subList(0, emptyLineIndex)
        val instructionLines = lines.subList(emptyLineIndex + 1, lines.size - 1)

        return parseCargoStateLines(cargoStateLines) to parseInstructionLines(instructionLines)
    }

    private fun parseCargoStateLines(lines: List<String>): CargoState = lines
        .dropLast(1)
        .flatMap { line ->
            line
                .chunked(4)
                .map { it[1] }
                .mapIndexed { index, char -> index + 1 to Crate(char.toString()) }
                .filter { (_, v) -> v.id != " " }
        }
        .groupBy({ (k, _) -> k }) { (_, v) -> v }
        .mapValues { (_, v) -> Stack(v) }
        .let(::CargoState)

    private fun parseInstructionLines(lines: List<String>): List<MoveInstruction> = lines
        .map(String::trim)
        .map { line ->
            val split = line.split(" ")
            MoveInstruction(split[1].toInt(), split[3].toInt(), split[5].toInt())
        }

    fun runInstructions(
        cargoState: CargoState,
        instructions: List<MoveInstruction>,
        moveMultipleCrates: Boolean = false
    ): CargoState = instructions
        .fold(cargoState) { acc, curr -> acc.executeInstruction(curr, moveMultipleCrates) }


}
