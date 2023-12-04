package com.advcode.days

import com.advcode.days.models.Game
import java.io.File

class Day2(override val resPath: String) : Solution {

    private val rLimit = 12
    private val gLimit = 13
    private val bLimit = 14

    override fun solvePart1(file: File) {
        file.parse().filter {
            it.r <= rLimit && it.g <= gLimit && it.b <= bLimit
        }.sumOf { it.id }.run {
            println(this)
        }
    }

    override fun solvePart2(file: File) {
        file.parse().sumOf {
            it.r * it.g * it.b
        }.run {
            println(this)
        }
    }

    private fun File.parse(): List<Game> {
        return readLines().map { line ->
            val items = line.split(": ", "; ", ", ")
            val group = items.subList(1, items.size).map { ball ->
                ball.split(" ").let {
                    Pair(it.last(), it.first().toInt())
                }
            }.groupBy(keySelector = { it.first }, valueTransform = { it.second })
            Game(
                id = items.first().removePrefix("Game ").toInt(),
                r = group["red"]?.max() ?: 0,
                g = group["green"]?.max() ?: 0,
                b = group["blue"]?.max() ?: 0,
            )
        }
    }

}


