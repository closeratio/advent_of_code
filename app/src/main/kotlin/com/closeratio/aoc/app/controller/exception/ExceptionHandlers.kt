package com.closeratio.aoc.app.controller.exception

import com.closeratio.aoc.app.controller.RunnerController.*
import org.springframework.http.HttpStatus.*
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(RunnerYearNotFoundException::class)
    fun handleRunnerNotFoundException(exception: RunnerYearNotFoundException): ProblemDetail =
        ProblemDetail.forStatusAndDetail(
            NOT_FOUND,
            exception.message!!
        )

    @ExceptionHandler(RunnerDayNotFoundException::class)
    fun handleRunnerNotFoundException(exception: RunnerDayNotFoundException): ProblemDetail =
        ProblemDetail.forStatusAndDetail(
            NOT_FOUND,
            exception.message!!
        )

    @ExceptionHandler(InvalidPartException::class)
    fun handleInvalidPartException(exception: InvalidPartException): ProblemDetail = ProblemDetail.forStatusAndDetail(
        BAD_REQUEST,
        exception.message!!
    )

    @ExceptionHandler(RunnerException::class)
    fun handleRunnerException(exception: RunnerException): ProblemDetail = ProblemDetail.forStatusAndDetail(
        INTERNAL_SERVER_ERROR,
        exception.message ?: ""
    )

}
