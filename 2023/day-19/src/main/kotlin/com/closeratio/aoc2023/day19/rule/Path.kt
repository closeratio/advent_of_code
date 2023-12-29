package com.closeratio.aoc2023.day19.rule

data class Path(
    val rules: List<Rule>
) {
    operator fun plus(it: Rule) = Path(rules + it)
}
