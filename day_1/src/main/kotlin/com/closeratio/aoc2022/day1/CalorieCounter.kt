package com.closeratio.aoc2022.day1

import com.closeratio.aoc2022.common.ResourceLoader
import org.springframework.stereotype.Service

@Service
class CalorieCounter(
    private val resourceLoader: ResourceLoader
) {

    private fun convertToGroups(
        path: String
    ): List<CalorieGroup> = resourceLoader.loadResource(path)
        .split("\n\n")
        .map { lines ->
            lines.split("\n").map(String::toLong).let(::CalorieGroup)
        }

    fun largestCalorieGroup(
        path: String
    ): CalorieGroup = convertToGroups(path).maxBy(CalorieGroup::total)

    fun top3Groups(
        path: String
    ): List<CalorieGroup> = convertToGroups(path)
        .sortedBy(CalorieGroup::total)
        .takeLast(3)

}
