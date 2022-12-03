package com.closeratio.aoc.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.closeratio")
class AdventOfCode2022Application

fun main(args: Array<String>) {
	runApplication<AdventOfCode2022Application>(*args)
}
