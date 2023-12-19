package com.closeratio.aoc2023.day15

import org.springframework.stereotype.Component
import java.util.*

@Component
class LensManager(
    private val hashGenerator: HashGenerator
) {

    fun computeFocusingPower(
        data: String
    ): Long {
        val boxes = mutableMapOf<Long, LinkedList<Lens>>()

        data.split(",")
            .forEach { instruction ->
                val id = if (instruction.endsWith("-")) {
                    instruction.dropLast(1)
                } else {
                    instruction.split("=").first()
                }
                val hash = hashGenerator.computeHash(id)
                val box = boxes.getOrPut(hash) { LinkedList() }

                if (instruction.endsWith("-")) {
                    box.remove(Lens(id, 0))
                } else {
                    val focalLengthString = instruction.split("=").last()
                    val lens = Lens(id, focalLengthString.toLong())
                    if (lens in box) {
                        box.first { it.id == id }.focalLength = lens.focalLength
                    } else {
                        box.add(lens)
                    }
                }
            }

        return boxes
            .entries
            .flatMap { (boxNumber, lenses) ->
                lenses.mapIndexed { index, lens ->
                    (boxNumber + 1) * (index + 1) * lens.focalLength
                }
            }
            .sum()
    }

}
