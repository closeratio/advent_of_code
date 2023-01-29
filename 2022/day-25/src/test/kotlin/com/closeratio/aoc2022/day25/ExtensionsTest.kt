package com.closeratio.aoc2022.day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ExtensionsTest {

    companion object {
        @JvmStatic
        fun snafuToLongArgs(): List<Arguments> = listOf(
            arguments("1=-0-2", 1747),
            arguments("12111", 906),
            arguments("2=0=", 198),
            arguments("21", 11),
            arguments("2=01", 201),
            arguments("111", 31),
            arguments("20012", 1257),
            arguments("112", 32),
            arguments("1=-1=", 353),
            arguments("1-12", 107),
            arguments("12", 7),
            arguments("1=", 3),
            arguments("122", 37)
        )

        @JvmStatic
        fun longToSnafuArgs(): List<Arguments> = listOf(
            arguments(1, "1"),
            arguments(2, "2"),
            arguments(3, "1="),
            arguments(4, "1-"),
            arguments(5, "10"),
            arguments(6, "11"),
            arguments(7, "12"),
            arguments(8, "2="),
            arguments(9, "2-"),
            arguments(10, "20"),
            arguments(15, "1=0"),
            arguments(20, "1-0"),
            arguments(2022, "1=11-2"),
            arguments(12345, "1-0---0"),
            arguments(314159265, "1121-1110-1=0"),

            arguments(25, "100")
        )
    }

    @ParameterizedTest
    @MethodSource("snafuToLongArgs")
    fun snafuToLong_givenInputValue_convertsToExpectedValue(
        input: String,
        expected: Long
    ) {
        val result = input.snafuToLong()
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("longToSnafuArgs")
    fun longToSnafu_givenInputValue_convertsToExpectedValue(
        input: Long,
        expected: String
    ) {
        val result = input.toSnafu()
        assertThat(result).isEqualTo(expected)
    }

}