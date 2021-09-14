package com.github.shwaka.parautil

import io.kotest.core.NamedTag
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

val parallelTag = NamedTag("Parallel")

class ParallelTest : FreeSpec({
    tags(parallelTag)

    "pmap should return the same result as map" {
        val intList = listOf(1, 4, 2, 3)
        val transform: (Int) -> Int = { it * 10 }
        intList.pmap(transform) shouldBe intList.map(transform)
    }

    "pmapNotNull should return the same result as map" {
        val intList = listOf(1, 0, 2, 5, 0, 3)
        val transform: (Int) -> Int? = { if (it == 0) null else it }
        intList.pmapNotNull(transform) shouldBe intList.mapNotNull(transform)
    }

    "pmapIndexed should return the same result as map" {
        val intList = listOf(1, 4, 2, 3)
        val transform: (Int, Int) -> Int = { i, j -> i + j }
        intList.pmapIndexed(transform) shouldBe intList.mapIndexed(transform)
    }

    "pmapIndexedNotNull should return the same result as map" {
        val intList = listOf(1, 0, 2, 5, 0, 3)
        val transform: (Int, Int) -> Int? = { i, j -> if (j == 0) null else i + j }
        intList.pmapIndexedNotNull(transform) shouldBe intList.mapIndexedNotNull(transform)
    }

    "pforEach should do the same as forEach except for the order" {
        val intList = listOf(1, 4, 2, 3)
        val list1 = mutableListOf<Int>()
        intList.pforEach { list1.add(it) }
        val list2 = mutableListOf<Int>()
        intList.forEach { list2.add(it) }
        list1.sorted() shouldBe list2.sorted()
    }
})
