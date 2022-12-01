package com.closeratio.aoc2022.common

import org.springframework.stereotype.Component

@Component
class ResourceLoader {

    fun loadResource(path: String): String = javaClass
        .getResource(path)!!
        .readText()
        .trim()

}
