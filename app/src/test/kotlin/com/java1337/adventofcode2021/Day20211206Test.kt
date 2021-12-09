package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211206Test {

    private val sample = listOf(
        "3,4,3,1,2"
    )

    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211206(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("5934", actual)
    }

    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211206(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("26984457539", actual)
    }
}