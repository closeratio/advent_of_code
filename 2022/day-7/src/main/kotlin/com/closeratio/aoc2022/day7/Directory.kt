package com.closeratio.aoc2022.day7

data class Directory(
    val name: String
) {

    val childDirectories: MutableList<Directory> = mutableListOf()
    val childFiles: MutableList<File> = mutableListOf()

    fun totalSize(): Long = childFiles.sumOf(File::size) + childDirectories.sumOf(Directory::totalSize)

    fun getAllDirectories(): List<Directory> = childDirectories.flatMap(Directory::getAllDirectories) + this

    fun getAllDirectories(
        maxSize: Long
    ): List<Directory> = getAllDirectories().filter { it.totalSize() <= maxSize }

}
