package com.closeratio.aoc2024.day5

import org.springframework.stereotype.Service
import java.lang.System.lineSeparator

@Service
class PrintQueueValidator {

    private val lineSep = lineSeparator()

    private fun parseRulesAndQueues(
        text: String
    ): Pair<List<OrderRule>, List<PrintQueue>> {
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

        return rules to queues
    }

    fun sumMiddlePageNumbers(
        text: String,
        validQueues: Boolean = true
    ): Long {
        val (rules, queues) = parseRulesAndQueues(text)

        val filteredQueues = if (validQueues) {
            queues.filter { it.isValid(rules) }
        } else {
            queues.filterNot { it.isValid(rules) }.map { it.sort(rules) }
        }

        val middlePageNumbers = filteredQueues.map { it.middlePage().toLong() }
        return middlePageNumbers.sum()
    }


}