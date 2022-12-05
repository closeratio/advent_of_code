package com.closeratio.aoc2022.day5

import com.closeratio.aoc.common.AocTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@AocTest
class InstructionRunnerTest {

    @Autowired
    private lateinit var instructionRunner: InstructionRunner

    @Test
    fun executeInstruction_eachState_producesExpectedNextStates() {
        val (initialState, instructions) = instructionRunner.loadState("/test_input.txt")

        val state2 = initialState.executeInstruction(instructions[0])
        assertThat(state2)
            .isEqualTo(
                CargoState(
                    mapOf(
                        1 to Stack(listOf(Crate("D"), Crate("N"), Crate("Z"))),
                        2 to Stack(listOf(Crate("C"), Crate("M"))),
                        3 to Stack(listOf(Crate("P")))
                    )
                )
            )

        val state3 = state2.executeInstruction((instructions[1]))
        assertThat(state3)
            .isEqualTo(
                CargoState(
                    mapOf(
                        1 to Stack(emptyList()),
                        2 to Stack(listOf(Crate("C"), Crate("M"))),
                        3 to Stack(listOf(Crate("Z"), Crate("N"), Crate("D"), Crate("P")))
                    )
                )
            )

        val state4 = state3.executeInstruction((instructions[2]))
        assertThat(state4)
            .isEqualTo(
                CargoState(
                    mapOf(
                        1 to Stack(listOf(Crate("M"), Crate("C"))),
                        2 to Stack(emptyList()),
                        3 to Stack(listOf(Crate("Z"), Crate("N"), Crate("D"), Crate("P")))
                    )
                )
            )

        val state5 = state4.executeInstruction((instructions[3]))
        assertThat(state5)
            .isEqualTo(
                CargoState(
                    mapOf(
                        1 to Stack(listOf(Crate("C"))),
                        2 to Stack(listOf(Crate("M"))),
                        3 to Stack(listOf(Crate("Z"), Crate("N"), Crate("D"), Crate("P")))
                    )
                )
            )
    }

    @Test
    fun runInstructions_givenTestInput_producesExpectedState() {
        val (initialState, instructions) = instructionRunner.loadState("/test_input.txt")

        val state = instructionRunner.runInstructions(initialState, instructions)
        val topLineString = state.topLineString()
        assertThat(topLineString).isEqualTo("CMZ")
    }

    @Test
    fun runInstructionsMultipleCreates_givenTestInput_producesExpectedState() {
        val (initialState, instructions) = instructionRunner.loadState("/test_input.txt")

        val state = instructionRunner.runInstructions(initialState, instructions, true)
        val topLineString = state.topLineString()
        assertThat(topLineString).isEqualTo("MCD")
    }

}
