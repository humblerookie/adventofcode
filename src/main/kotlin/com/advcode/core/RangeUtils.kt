package com.advcode.core

// Splits thing into overlapping and non overlapping ranges
fun LongRange.splitRanges(target: LongRange): Pair<LongRange?, List<LongRange>> {
    val resultList = mutableListOf<LongRange>()
    var overlappingRange: LongRange? = null

    if (last < target.first || first > target.last) {
        // If there is no intersection, return the source range as non-overlapping
        resultList.add(this)
    } else {
        // If there is an intersection, split the source range accordingly
        if (first < target.first) {
            resultList.add(first..<target.first)
        }
        if (last > target.last) {
            resultList.add((target.last + 1)..last)
        }
        // Add the intersecting range
        overlappingRange = maxOf(first, target.first)..minOf(last, target.last)
    }

    return Pair(overlappingRange, resultList)
}