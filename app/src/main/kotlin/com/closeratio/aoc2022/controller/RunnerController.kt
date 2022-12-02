package com.closeratio.aoc2022.controller

import com.closeratio.aoc2022.common.AocRunner
import com.closeratio.aoc2022.controller.response.RunResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/run")
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

    @PostMapping
    fun run(
        @RequestParam("day") day: Int?,
        @RequestParam("part") part: Int?
    ): RunResponse {

        // Get the latest day if the day isn't provided
        val derivedDay = day ?: runnerMap.keys.maxBy { it }
        val runner = runnerMap[derivedDay] ?: throw RunnerNotFoundException(derivedDay)

        // Get the latest part if the part isn't provided
        val derivedPart = when (part) {
            1 -> 1
            2 -> 2
            null -> {
                if (runner.part2Function() != null) 2 else 1
            }
            else -> throw InvalidPartException(part)
        }

        val function = when (derivedPart) {
            1 -> runner.part1Function()
            2 -> runner.part2Function()
            else -> throw IllegalStateException()
        } ?: throw IllegalStateException()

        return try {
            log.info("Running day $derivedDay part $derivedPart")
            val startTime = LocalDateTime.now()
            val result = function()
            val endTime = LocalDateTime.now()
            val duration = Duration.between(startTime, endTime)
            log.info("Result is: $result")
            log.info("Took ${duration.toMillis()}ms")
            RunResponse(
                derivedDay,
                derivedPart,
                result,
                "${duration.toMillis()}ms"
            )
        } catch (ex: Exception) {
            log.error("Exception thrown running $derivedDay part $derivedPart", ex)
            throw RunnerException(ex)
        }
    }

    class RunnerNotFoundException(day: Int) : RuntimeException("Runner not found for day $day")

    class InvalidPartException(part: Int) : RuntimeException("Each runner only has part 1 and 2. Requested $part")

    class RunnerException(cause: Exception) : RuntimeException(cause)

}
