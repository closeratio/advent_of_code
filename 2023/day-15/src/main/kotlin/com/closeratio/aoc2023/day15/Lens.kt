package com.closeratio.aoc2023.day15

class Lens(
    val id: String,
    var focalLength: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lens

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Lens(id='$id', focalLength=$focalLength)"
    }

}
