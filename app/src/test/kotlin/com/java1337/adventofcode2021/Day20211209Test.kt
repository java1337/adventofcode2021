package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211209Test {

    private val sample = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678",
    )

    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211209(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("15", actual)
    }

    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211209(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("1134", actual)
    }
}