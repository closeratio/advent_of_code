rootProject.name = "advent-of-code"

include(listOf(
    "common",
    "app"
) + (2015..2023).flatMap { year ->
    (1..25).map { day -> "$year:day-$day" } + "$year:common"
})
