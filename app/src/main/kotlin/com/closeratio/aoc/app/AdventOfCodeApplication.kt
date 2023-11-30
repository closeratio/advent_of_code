package com.closeratio.aoc.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.closeratio")
class AdventOfCodeApplication

fun main(args: Array<String>) {
	runApplication<AdventOfCodeApplication>(*args)
}
