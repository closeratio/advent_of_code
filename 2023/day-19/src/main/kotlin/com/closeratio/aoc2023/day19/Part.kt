package com.closeratio.aoc2023.day19

data class Part(
    val x: Long,
    val m: Long,
    val a: Long,
    val s: Long
) {
    val sum = x + m + a + s
}
