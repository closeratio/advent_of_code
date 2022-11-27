package com.closeratio.aoc2022.controller

import com.closeratio.aoc2022.common.AocRunner
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/run")
class RunnerController(
    runners: List<AocRunner>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val runnerMap = runners.associateBy(AocRunner::getDay)

    init {
        runnerMap
            .values
            .sortedBy(AocRunner::getDay)
            .forEach {
                log.info("Registered day ${it.getDay()} runner (${it.javaClass.simpleName})")
            }
    }

    @PostMapping("/day/{day}/part/{part}")
    fun run(
        @PathVariable day: Int,
        @PathVariable part: Int
    ): String {
        val runner = runnerMap[day] ?: throw RunnerNotFoundException(day)

        val function =  when (part) {
            1 -> runner::runPart1
            2 -> runner::runPart2
            else -> throw InvalidPartException(part)
        }

        return try {
            log.info("Running day $day part $part")
            val result = function()
            log.info("Result is: $result")
            result
        } catch (ex: Exception) {
            throw RunnerException(ex)
        }
    }

    class RunnerNotFoundException(day: Int): RuntimeException("Runner not found for day $day")

    class InvalidPartException(part: Int): RuntimeException("Each runner only has part 1 and 2. Requested $part")

    class RunnerException(cause: Exception): RuntimeException(cause)

}