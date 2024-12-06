package com.closeratio.aoc2024.day5

import org.springframework.stereotype.Service
import java.lang.System.lineSeparator

@Service
class PrintQueueValidator {

    private val lineSep = lineSeparator()

    fun sumValidMiddlePageNumbers(
        text: String
    ): Long {
        val (ruleBlock, queueBlock) = text.split(lineSep + lineSep).map { it.trim() }

        val rules = ruleBlock
            .split(lineSep)
            .map {
                val (firstStr, secondStr) = it.split("|")
                OrderRule(firstStr.toInt(), secondStr.toInt())
            }

        val queues = queueBlock
            .split(lineSep)
            .map {
                PrintQueue(it.split(",").map { it.toInt() })
            }

        val validQueues = queues.filter { it.isValid(rules) }

        val middlePageNumbers = validQueues.map { it.pages[it.pages.size / 2].toLong() }
        return middlePageNumbers.sum()
    }

}