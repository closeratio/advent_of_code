package com.closeratio.aoc2022.day20

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component

@Component
class ListParser(
    private val resourceLoader: ResourceLoader
) {

    fun parse(
        path: String,
        decryptionKey: Long = 1
    ): ListDecrypter = resourceLoader
        .loadResourceLines(path)
        .map(String::toLong)
        .map { it * decryptionKey }
        .map(::ListItem)
        .let(::ListDecrypter)

}
