package com.closeratio.aoc.common

import org.springframework.stereotype.Component

@Component
class ResourceLoader {

    fun loadResourceText(path: String): String = javaClass
        .getResource(path)!!
        .readText()
        .trim()

    fun loadResourceLines(path: String): List<String> = loadResourceText(path)
        .split("\n")

}
