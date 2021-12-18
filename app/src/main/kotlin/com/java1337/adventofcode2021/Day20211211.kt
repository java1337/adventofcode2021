package com.java1337.adventofcode2021

import java.lang.annotation.IncompleteAnnotationException

//
// --- Day 11: Dumbo Octopus ---
// You enter a large cavern full of rare bioluminescent dumbo octopuses! They seem
// to not like the Christmas lights on your submarine, so you turn them off for now.
//
// There are 100 octopuses arranged neatly in a 10 by 10 grid. Each octopus slowly
// gains energy over time and flashes brightly for a moment when its energy is full.
// Although your lights are off, maybe you could navigate through the cave without
// disturbing the octopuses if you could predict when the flashes of light will happen.
//
// Each octopus has an energy level - your submarine can remotely measure the energy
// level of each octopus (your puzzle input). For example:
//      * 5483143223
//      * 2745854711
//      * 5264556173
//      * 6141336146
//      * 6357385478
//      * 4167524645
//      * 2176841721
//      * 6882881134
//      * 4846848554
//      * 5283751526
//
// The energy level of each octopus is a value between 0 and 9. Here, the top-left
// octopus has an energy level of 5, the bottom-right one has an energy level of 6,
// and so on.
//
// You can model the energy levels and flashes of light in steps. During a single
// step, the following occurs:
//      * First, the energy level of each octopus increases by 1.
//      * Then, any octopus with an energy level greater than 9 flashes.
//        This increases the energy level of all adjacent octopuses by 1,
//        including octopuses that are diagonally adjacent. If this
//        causes an octopus to have an energy level greater than 9, it
//        also flashes. This process continues as long as new octopuses
//        keep having their energy level increased beyond 9.
//        (An octopus can only flash at most once per step.)
//      * Finally, any octopus that flashed during this step has its
//        energy level set to 0, as it used all of its energy to flash.
//
// Adjacent flashes can cause an octopus to flash on a step even if it begins that step
// with very little energy. Consider the middle octopus with 1 energy in this situation:
//
//Before any steps:
//  11111
//  19991
//  19191
//  19991
//  11111
//
//After step 1:
//  34543
//  40004
//  50005
//  40004
//  34543
//
//After step 2:
//  45654
//  51115
//  61116
//  51115
//  45654
//
// An octopus is highlighted when it flashed during the given step.
//
// Here is how the larger example above progresses:
//
// Before any steps:
//  5483143223
//  2745854711
//  5264556173
//  6141336146
//  6357385478
//  4167524645
//  2176841721
//  6882881134
//  4846848554
//  5283751526
//
// After step 1:
//  6594254334
//  3856965822
//  6375667284
//  7252447257
//  7468496589
//  5278635756
//  3287952832
//  7993992245
//  5957959665
//  6394862637
//
// After step 2:
//  8807476555
//  5089087054
//  8597889608
//  8485769600
//  8700908800
//  6600088989
//  6800005943
//  0000007456
//  9000000876
//  8700006848
//
// After step 3:
//  0050900866
//  8500800575
//  9900000039
//  9700000041
//  9935080063
//  7712300000
//  7911250009
//  2211130000
//  0421125000
//  0021119000
//
// After step 4:
//  2263031977
//  0923031697
//  0032221150
//  0041111163
//  0076191174
//  0053411122
//  0042361120
//  5532241122
//  1532247211
//  1132230211
//
// After step 5:
//  4484144000
//  2044144000
//  2253333493
//  1152333274
//  1187303285
//  1164633233
//  1153472231
//  6643352233
//  2643358322
//  2243341322
//
// After step 6:
//  5595255111
//  3155255222
//  3364444605
//  2263444496
//  2298414396
//  2275744344
//  2264583342
//  7754463344
//  3754469433
//  3354452433
//
// After step 7:
//  6707366222
//  4377366333
//  4475555827
//  3496655709
//  3500625609
//  3509955566
//  3486694453
//  8865585555
//  4865580644
//  4465574644
//
// After step 8:
//  7818477333
//  5488477444
//  5697666949
//  4608766830
//  4734946730
//  4740097688
//  6900007564
//  0000009666
//  8000004755
//  6800007755
//
// After step 9:
//  9060000644
//  7800000976
//  6900000080
//  5840000082
//  5858000093
//  6962400000
//  8021250009
//  2221130009
//  9111128097
//  7911119976
//
// After step 10:
//  0481112976
//  0031112009
//  0041112504
//  0081111406
//  0099111306
//  0093511233
//  0442361130
//  5532252350
//  0532250600
//  0032240000
//
// After step 10, there have been a total of 204 flashes. Fast forwarding, here is
// the same configuration every 10 steps:
//
/// ...
// After 100 steps, there have been a total of 1656 flashes.
//
// Given the starting energy levels of the dumbo octopuses in your cavern, simulate
// 100 steps. How many total flashes are there after 100 steps?
//

class Day20211211(private val input: List<String>) : DailyChallenge {

    companion object {
        fun create(): Day20211211 {
            val input = Utils.readFileAsListOfString("Day20211211.txt")
            return Day20211211(input)
        }
    }


    private val grid : Grid<Int> = input.flatMapIndexed { row: Int, line: String ->
        line.mapIndexed { column: Int, c: Char ->
            Position(row, column) to c.digitToInt()
        }
    }.toMap(mutableMapOf())


    private val maxX = input.first().length
    private val maxY = input.size

    fun printGrid() {
        val sb = StringBuffer()
        for(x in 0 until maxX) {
            for (y in 0 until maxY) {
                sb.append(grid[Position(x,y)])
            }
            sb.append("\n")
        }
        sb.append("\n")
        println(sb.toString())
    }

    fun debug(s: String) {
//        println(s)
    }

    fun doStep(): Grid<Int> {
        fun incrementPosition(position: Position) {
            val oldValue = grid[position]!!
            if (oldValue == 9) {
                grid[position] = 0
            } else {
                grid[position] = 1 + oldValue
            }
        }

        fun filterInRange(position: Position): Boolean {
            return position.first in 0 until maxX && position.second in 0 until maxY
        }

        grid.forEach { entry -> incrementPosition(entry.key) }

        var zeroes: List<Pair<Int, Int>> = grid.filter { entry ->
            entry.value == 0
        }.map {
            it.key
        }

        var neighbors = zeroes
            .flatMap {
                debug("  $it -> ${it.neighbors()}")
                it.neighbors()
            }
            .filterNot { zeroes.contains(it) }
            .filter { filterInRange(it) }
        debug("zeroes = $zeroes")
        debug("neighbors = $neighbors")

        while (neighbors.isNotEmpty()) {
            val newNeighbors = neighbors.flatMap {
                if (filterInRange(it)) {
                    incrementPosition(it)
                    zeroes = zeroes.plus(it)
                    debug("     $it -> ${it.neighbors()}")
                    it.neighbors()
                } else {
                    listOf()
                }
            }.toSet()
            neighbors = newNeighbors.filterNot { zeroes.contains(it) }.filter { filterInRange(it) }
            debug("zeroes = $zeroes")
            debug("neighbors = $neighbors")
        }

        return grid
    }


    override fun first(): String {
        return (1 .. 100).sumOf {
            doStep()
            val zeroes = grid.values.count { i -> i == 0 }
            println("After step $it ($zeroes flashes)")
            printGrid()
            zeroes
        }.toString()
    }

    override fun second(): String {
        return "bar"
    }

    override fun getLabel() = "2021/12/11 - Dumbo Octopus"
}
