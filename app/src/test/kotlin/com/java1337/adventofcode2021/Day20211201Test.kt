package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211201Test {

    private val sample = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun shouldReturnSevenForSampleInputForFirstQuestion() {
        // given
        val runner = Day20211201(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("7", actual)
    }

    @Test
    fun shouldReturnFiveForSampleInputForFirstQuestion() {
        // given
        val runner = Day20211201(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("5", actual)
    }
}