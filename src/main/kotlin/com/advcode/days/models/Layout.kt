package com.advcode.days.models

data class LayoutNumber(
    val value: Long,
    val r: Int,
    val range: IntRange,
)

data class LayoutSymbol(
    val value: Char,
    val r: Int,
    val c: Int,
)