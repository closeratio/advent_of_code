rootProject.name = "advent_of_code_2022"

include(listOf(
    "common",
    "app"
) + (1..25).map { "day_$it" })