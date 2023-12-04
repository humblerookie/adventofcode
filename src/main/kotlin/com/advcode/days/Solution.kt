package com.advcode.days

import java.io.File

interface Solution {
    val resPath:String
    fun name () = this::class.simpleName!!.lowercase()
    val inputFile: File
        get() = File(resPath, name() + ".txt")
    val testFile: File
        get() = File(resPath, name() + "test.txt")

    fun solvePart1(file: File)

    fun solvePart2(file: File)
}