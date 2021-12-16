package com.java1337.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day20211210Test {

    private val sample = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]",
    )

    @Test
    fun shouldReturnCorrectValueForFirstQuestion() {
        // given
        val runner = Day20211210(sample)

        // when
        val actual = runner.first()

        // then
        assertEquals("26397", actual)
    }

    @Test
    fun shouldReturnCorrectValueForSecondQuestion() {
        // given
        val runner = Day20211210(sample)

        // when
        val actual = runner.second()

        // then
        assertEquals("288957", actual)
    }
}