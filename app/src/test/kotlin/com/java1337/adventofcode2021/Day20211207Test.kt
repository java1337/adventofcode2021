package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211207Test {

    private val sample = listOf(
        "16,1,2,0,4,2,7,1,2,14"
    )

    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211207(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("37", actual)
    }

    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211207(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("168", actual)
    }
}