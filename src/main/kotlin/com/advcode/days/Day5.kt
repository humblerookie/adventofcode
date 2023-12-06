package com.advcode.days

import com.advcode.core.splitRanges
import java.io.File

class Day5(override val resPath: String) : Solution {
    private val numRegex = Regex("\\d+")
    override fun solvePart1(file: File) {
        file.readText().split("\n\n")
            .map { i -> numRegex.findAll(i).map { it.value.toLong() }.toList() }
            .run {
                val seeds = first()
                val others = subList(1, this.size).map { it.chunked(3) }
                seeds.minOfOrNull { seed ->
                    others.fold(seed) { acc, maps ->
                        maps.firstNotNullOfOrNull {
                            val (d, s, r) = it
                            if (acc in s..(s + r)) (d - s + acc) else null
                        } ?: acc
                    }
                }
            }
            .also(::println)
    }

    override fun solvePart2(file: File) {
        file.readText().split("\n\n")
            .map { i -> numRegex.findAll(i).map { it.value.toLong() }.toList() }
            .run {
                val mappers = subList(1, this.size).map {
                    it.chunked(3).associate { (d, s, r) -> s..<s + r to d..<d + r }
                }
                val seeds = first().chunked(2).map { (f, s) -> f..<f + s }
                val entities = seeds.toMutableList()
                val processed = entities.toMutableList()
                mappers.forEach { maps ->
                    val items = processed.toMutableList()
                    processed.clear()
                    while (items.isNotEmpty()) {
                        val cur = items.removeFirst()
                        var hasSplit = false
                        for ((k, v) in maps) {
                            val (overlap, others) = cur.splitRanges(k)
                            if (overlap != null) {
                                val start = v.first - k.first
                                processed.add(start + overlap.first..start + overlap.last)
                                hasSplit = true
                                items.addAll(0, others)
                                break
                            }
                        }
                        if (!hasSplit) {
                            processed.add(cur)
                        }
                    }
                }
                processed.minOf { it.first }
            }

            .also(::println)
    }

}