package com.java1337.adventofcode2021

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211211Test {

    private val sample = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    @Ignore
    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211211(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("1656", actual)
    }

    @Ignore
    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211211(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("288957", actual)
    }

    @Ignore
    @Test
    fun shouldWorkForSmallSample() {
        // given
        val smallSample = listOf(
            "11111",
            "19991",
            "19191",
            "19991",
            "11111",
        )

        val runner = Day20211211(smallSample)

        runner.doStep()
        runner.printGrid()

        runner.doStep()
        runner.printGrid()

        runner.doStep()
        runner.printGrid()
    }
}