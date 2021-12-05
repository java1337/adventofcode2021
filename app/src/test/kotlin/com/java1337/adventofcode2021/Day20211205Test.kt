package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211205Test {

    private val sample = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211205(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("5", actual)
    }

    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211205(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("12", actual)
    }
}