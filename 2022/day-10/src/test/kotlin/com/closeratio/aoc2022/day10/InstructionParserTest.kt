package com.closeratio.aoc2022.day10

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class InstructionParserTest {

    @Autowired
    private lateinit var instructionParser: InstructionParser

    @Test
    fun parseInstructions_givenTestInputAndComputesSignalStrengthSum_returnsExpectedValue() {
        val computer = instructionParser.parseInstructions("/test_input.txt")
        val result = computer.computeSignalStrengthSum()

        assertThat(result).isEqualTo(13_140)
    }

    @Test
    fun parseInstructins_givenTestInputAndGenerateCrtString_returnsExpectedValue() {
        val computer = instructionParser.parseInstructions("/test_input.txt")
        val result = computer.generateCrtString()

        assertThat(result).isEqualTo(
            """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
        """.trimIndent()
        )
    }

}
