package com.advcode.days

import com.advcode.days.models.Card
import java.io.File

class Day4(override val resPath: String) : Solution {

    private val numRegex = Regex("\\d+")
    override fun solvePart1(file: File) {
        file.parseData().sumOf {
            if (it.hasWon) 1.shl(it.wins.count() - 1) else 0
        }.run { println(this) }
    }

    override fun solvePart2(file: File) {
        val data = file.parseData().sortedBy { it.id }
        val count = MutableList(data.size) { 1 }
        data.forEachIndexed { index, card ->
            repeat(card.wins.count() * count[index]) { i ->
                val pos = i / count[index]
                if (index + pos + 1 < data.size) {
                    count[index + pos + 1]++
                }
            }
        }
        println(count.sum())
    }


    private fun File.parseData() = readLines().map { line ->
        val items = numRegex.findAll(line).toList()
        val nums = items.subList(1, items.size)
        val sepIndex = line.indexOf("|")
        Card(
            id = items.first().value.toInt(),
            winners = nums.filter { it.range.first < sepIndex }.map { it.value.toInt() },
            draws = nums.filter { it.range.first > sepIndex }.map { it.value.toInt() }
        )

    }

}
