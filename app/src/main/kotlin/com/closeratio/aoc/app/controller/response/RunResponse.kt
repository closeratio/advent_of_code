package com.closeratio.aoc.app.controller.response

data class RunResponse(
    val year: Int,
    val day: Int,
    val part: Int,
    val result: Any,
    val executionTime: String
)
