package com.closeratio.aoc.common

import org.springframework.stereotype.Component

@Component
class ResourceLoader {

    fun loadResourceText(
        path: String,
        trim: Boolean = true
    ): String = javaClass
        .getResource(path)!!
        .readText()
        .let {
            if (trim) it.trim() else it
        }

    fun loadResourceLines(
        path: String,
        trim: Boolean = true
    ): List<String> = loadResourceText(path, trim)
        .split(System.lineSeparator())

}
