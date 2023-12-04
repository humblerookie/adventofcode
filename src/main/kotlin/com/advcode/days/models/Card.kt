package com.advcode.days.models

data class Card(
    val id: Int,
    val winners: List<Int>,
    val draws: List<Int>,
){
    val wins = winners.intersect(draws.toSet())
    val hasWon = wins.isNotEmpty()
}
