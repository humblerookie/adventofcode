package com.advcode.days

import java.io.File

class Day1(override val resPath: String) : Solution {
    override fun solvePart1(file: File) {
        val result = file.readLines().solve()
        println(result)
    }

    override fun solvePart2(file: File) {
        val result = file.readLines().map {
            it.lowercase().replaceNumbers()
        }.solve()
        println(result)
    }

    private fun List<String>.solve(): Int {
        return map { line ->
            line.filter { it.isDigit() }.asIterable()
        }.sumOf {
            "${it.first().digitToInt()}${it.last().digitToInt()}".toInt()
        }
    }

    private val digits = mapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    private fun String.replaceNumbers(): String {
        val sb = StringBuilder(this)
        digits.flatMap { e ->
            indexesOf(e.key).map { it to e.value }
        }.sortedBy {
            it.first
        }.forEach {
         sb.setCharAt(it.first, it.second.digitToChar())
        }
        return sb.toString()
    }

    private fun String.indexesOf(sub: String): List<Int> {
        return let {
            val regex = Regex(sub)
            regex.findAll(this).map { it.range.first }.toList()
        }
    }


}
