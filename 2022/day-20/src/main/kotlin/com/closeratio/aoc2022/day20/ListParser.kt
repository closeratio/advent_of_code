package com.closeratio.aoc2022.day20

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class ListParser(
    private val resourceLoader: ResourceLoader
) {

    fun parse(path: String): ListDecrypter = resourceLoader
        .loadResourceLines(path)
        .map(String::toInt)
        .map(::ListItem)
        .let(::ListDecrypter)

}
