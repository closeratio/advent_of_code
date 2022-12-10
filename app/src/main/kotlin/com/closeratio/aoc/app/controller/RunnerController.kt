package com.closeratio.aoc.app.controller

import com.closeratio.aoc.app.controller.response.RunResponse
import com.closeratio.aoc.common.AocRunner
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

    private val runnerMap = runners
        .groupBy(AocRunner::getYear)
        .mapValues { (_, runners) -> runners.associateBy(AocRunner::getDay) }

    init {
        runnerMap
            .values
            .flatMap(Map<Int, AocRunner>::values)
            .sortedBy(AocRunner::getDay)
            .sortedBy(AocRunner::getYear)
            .forEach {
                log.info("Registered year ${it.getYear()} day ${it.getDay()} runner (${it.javaClass.name})")
            }
    }

    @PostMapping
    fun run(
        @RequestParam("year") year: Int?,
        @RequestParam("day") day: Int?,
        @RequestParam("part") part: Int?
    ): RunResponse {

        // Get the latest year if the year isn't provided
        val derivedYear = year ?: runnerMap.keys.maxBy { it }
        val currentYearRunnerMap = runnerMap[derivedYear] ?: throw RunnerYearNotFoundException(derivedYear)

        // Get the latest day if the day isn't provided
        val derivedDay = day ?: currentYearRunnerMap.keys.maxBy { it }
        val runner = currentYearRunnerMap[derivedDay] ?: throw RunnerDayNotFoundException(derivedYear, derivedDay)

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
            val padding = when (result) {
                is String -> "\n"
                else -> ""
            }
            log.info("Result is: $padding$result")
            log.info("Took ${duration.toMillis()}ms")
            RunResponse(
                derivedYear,
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

    class RunnerYearNotFoundException(year: Int) : RuntimeException("No runners found for year $year")

    class RunnerDayNotFoundException(year: Int, day: Int) : RuntimeException("Runner not found for year $year day $day")

    class InvalidPartException(part: Int) : RuntimeException("Each runner only has part 1 and 2. Requested $part")

    class RunnerException(cause: Exception) : RuntimeException(cause)

}
