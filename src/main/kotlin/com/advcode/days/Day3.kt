package com.advcode.days

import com.advcode.days.models.LayoutNumber
import com.advcode.days.models.LayoutSymbol
import java.io.File

class Day3(override val resPath: String) : Solution {


    override fun solvePart1(file: File) {
        val i = file.parse()
        val nums = i.first
        val syms = i.second
        val sum = syms.flatMap {s->
            nums.filter {
                it.r in (s.r-1 .. s.r+1) && s.c in (it.range.first-1 .. it.range.last+1)
            }
        }.distinct().sumOf { it.value }
        println(sum)
    }

    override fun solvePart2(file: File) {
        val i = file.parse()
        val nums = i.first
        val syms = i.second
        val sum = syms.mapNotNull {s->
            val gears = nums.filter {
                it.r in (s.r-1 .. s.r+1) && s.c in (it.range.first-1 .. it.range.last+1)
            }
            if(gears.size ==2)  gears.map { it.value }.reduce(Long::times) else null
        }.sum()
        println(sum)
    }
    private val numRegex = Regex("\\d+")
    private val symbolRegex = Regex("[^\\w\\s.]+")

    private fun File.parse():Pair<List<LayoutNumber>, List<LayoutSymbol>> {
        val numbers = mutableListOf<LayoutNumber>()
        val symbols = mutableListOf<LayoutSymbol>()
        readLines().forEachIndexed { index,  line ->
            numbers.addAll(numRegex.findAll(line)
                .map { LayoutNumber(it.value.toLong(), index,it.range)}
                .toList())
            symbols.addAll(symbolRegex.findAll(line)
                .map { LayoutSymbol(it.value.first(), index,it.range.first) }
                .toList())
        }
        return  Pair(numbers,symbols)
    }

}


