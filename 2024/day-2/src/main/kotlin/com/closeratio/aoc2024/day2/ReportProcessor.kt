package com.closeratio.aoc2024.day2

import org.springframework.stereotype.Service

@Service
class ReportProcessor {

    private fun parseReport(line: String): Report = line
        .split(" ")
        .map(String::toLong)
        .let(::Report)

    fun countSafeReports(reports: List<String>): Long = reports
        .map(::parseReport)
        .filter(Report::isSafe)
        .size
        .toLong()

}