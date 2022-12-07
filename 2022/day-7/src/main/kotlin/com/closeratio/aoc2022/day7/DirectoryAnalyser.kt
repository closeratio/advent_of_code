package com.closeratio.aoc2022.day7

import com.closeratio.aoc.common.ResourceLoader
import org.springframework.stereotype.Component
import java.util.*

@Component
class DirectoryAnalyser(
    private val resourceLoader: ResourceLoader
) {

    data class CommandResultPair(
        val command: Command,
        val results: List<Result>
    )

    private fun computePairs(lines: List<String>): List<CommandResultPair> = lines.fold(
        emptyList()
    ) { acc, curr ->
        if (curr.startsWith("$")) {
            acc + CommandResultPair(
                Command(curr.replace("\\$\\s+".toRegex(), "")),
                mutableListOf()
            )
        } else {
            (acc.last().results as MutableList).add(
                Result(curr)
            )
            acc
        }
    }

    fun computeDirectoryStructure(
        currentDirectory: Directory,
        commands: LinkedList<CommandResultPair>
    ) {
        while (commands.isNotEmpty()) {
            val (command, results) = commands.pop()
            if (command.value.startsWith("cd")) {
                val target = command.value.split(" ").last()
                if (target == "..") {
                    return
                }

                computeDirectoryStructure(
                    currentDirectory.childDirectories.first { it.name == target },
                    commands
                )
            } else if (command.value.startsWith("ls")) {
                results.forEach { result ->
                    if (result.value.startsWith("dir")) {
                        val name = result.value.split(" ").last()
                        currentDirectory.childDirectories.add(Directory(name))
                    } else {
                        val (size, name) = result.value.split(" ")
                        currentDirectory.childFiles.add(File(name, size.toLong()))
                    }
                }
            }
        }
    }

    fun parseDirectoryStructure(path: String): Directory {
        val commands = computePairs(resourceLoader.loadResourceLines(path))

        val root = Directory("/")
        computeDirectoryStructure(
            root,
            LinkedList(commands.drop(1)) // We can drop the first entry because it's always a cd to the root
        )
        return root
    }

    fun sumDirectoriesWithMaxSize(
        path: String,
        maxSize: Long
    ): Long = parseDirectoryStructure(path)
        .getAllDirectories(maxSize)
        .sumOf(Directory::totalSize)

    fun computeDirectorySizeToDelete(
        path: String
    ): Long {
        val root = parseDirectoryStructure(path)
        val rootSize = root.totalSize()
        val freeSpace = 70_000_000 - rootSize
        val spaceToFreeUp = 30_000_000 - freeSpace

        return root
            .getAllDirectories()
            .map(Directory::totalSize)
            .sorted()
            .first { it >= spaceToFreeUp }
    }

}

