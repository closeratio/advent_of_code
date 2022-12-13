package com.closeratio.aoc2022.day13

abstract class Item : Comparable<Item> {

    abstract fun toOriginalString(): String

}
