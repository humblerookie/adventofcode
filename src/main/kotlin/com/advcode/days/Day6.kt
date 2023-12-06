package com.advcode.days

import java.io.File

class Day6(override val resPath: String) : Solution {
    private val numRegex = Regex("\\d+")
    override fun solvePart1(file: File) {
        val (time, distance) = file.readLines().map {
            numRegex.findAll(it).map { it.value.toLong() }.toList()
        }
        time.mapIndexed { i, t -> Pair(t, distance[i]) }
            .map { item -> (1..<item.first).map { it * (item.first - it) }.count { it > item.second } }
            .reduce(Int::times)
            .also(::println)

    }



    override fun solvePart2(file: File) {
        val (time, distance) = file.readLines().map {
            it.split(":")[1].replace(" ","").toLong()
        }
        listOf(Pair(time,distance))
            .map { item -> (1..<item.first).map { it * (item.first - it) }.count { it > item.second } }
            .first()
            .also(::println)

    }

}