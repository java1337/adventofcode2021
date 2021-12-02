package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211202Test {

    private val sample = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    @Test
    fun shouldReturnOneFiftyForSampleInputForFirstQuestion() {
        // given
        val runner = Day20211202(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("150", actual)
    }

    @Test
    fun shouldReturnNineHundredForSampleInputForFirstQuestion() {
        // given
        val runner = Day20211202(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("900", actual)
    }
}