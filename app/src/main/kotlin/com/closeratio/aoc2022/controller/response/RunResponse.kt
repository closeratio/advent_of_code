package com.closeratio.aoc2022.controller.response

data class RunResponse(
    val day: Int,
    val part: Int,
    val result: Any,
    val executionTime: String
)
