rootProject.name = "advent-of-code"

val years = 2015..2024
val days = 1..25

val projectNames = years.flatMap { year ->
    days.map { day -> "$year:day-$day" } + "$year:common"
}

include(listOf(
    "common",
    "app"
) + projectNames)

projectNames.forEach {
    name -> project(":$name").projectDir.mkdirs()
}
