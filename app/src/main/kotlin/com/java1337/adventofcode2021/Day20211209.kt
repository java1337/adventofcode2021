package com.java1337.adventofcode2021

//
// --- Day 9: Smoke Basin ---
// These caves seem to be lava tubes. Parts are even still volcanically active; small
// hydrothermal vents release smoke into the caves that slowly settles like rain.
//
// If you can model how the smoke flows through the caves, you might be able to avoid
// it and be that much safer. The submarine generates a heightmap of the floor of the
// nearby caves for you (your puzzle input).
//
// Smoke flows to the lowest point of the area it's in. For example, consider the
// following heightmap:
//     * 2199943210
//     * 3987894921
//     * 9856789892
//     * 8767896789
//     * 9899965678
//
// Each number corresponds to the height of a particular location, where 9 is the
// highest and 0 is the lowest a location can be.
//
// Your first goal is to find the low points - the locations that are lower than any
// of its adjacent locations. Most locations have four adjacent locations (up, down,
// left, and right); locations on the edge or corner of the map have three or two
// adjacent locations, respectively. (Diagonal locations do not count as adjacent.)
//
// In the above example, there are four low points, all highlighted: two are in the
// first row (a 1 and a 0), one is in the third row (a 5), and one is in the bottom
// row (also a 5). All other locations on the heightmap have some lower adjacent
// location, and so are not low points.
//
// The risk level of a low point is 1 plus its height. In the above example, the risk
// levels of the low points are 2, 1, 6, and 6. The sum of the risk levels of all low
// points in the heightmap is therefore 15.
//
// Find all the low points on your heightmap. What is the sum of the risk levels
// of all low points on your heightmap?
//
// --- Part Two ---
// Next, you need to find the largest basins so you know what areas are most important
// to avoid.
//
// A basin is all locations that eventually flow downward to a single low point. Therefore,
// every low point has a basin, although some basins are very small. Locations of height 9
// do not count as being in any basin, and all other locations will always be part of
// exactly one basin.
//
// The size of a basin is the number of locations within the basin, including the low point.
// The example above has four basins.
//
// The top-left basin, size 3:
//     * 2199943210
//     * 3987894921
//     * 9856789892
//     * 8767896789
//     * 9899965678
//
// The top-right basin, size 9:
//     * 2199943210
//     * 3987894921
//     * 9856789892
//     * 8767896789
//     * 9899965678
//
// The middle basin, size 14:
//     * 2199943210
//     * 3987894921
//     * 9856789892
//     * 8767896789
//     * 9899965678
//
// The bottom-right basin, size 9:
//     * 2199943210
//     * 3987894921
//     * 9856789892
//     * 8767896789
//     * 9899965678
//
// Find the three largest basins and multiply their sizes together. In the above example,
// this is 9 * 14 * 9 = 1134.
//
// What do you get if you multiply together the sizes of the three largest basins?




class Day20211209(private val input: List<String>) : DailyChallenge {

    companion object {
        fun create(): Day20211209 {
            val input = Utils.readFileAsListOfString("Day20211209.txt")
            return Day20211209(input)
        }
    }

    enum class Direction(val deltaX: Int, val deltaY: Int) {
        NORTH(0,1),
        SOUTH(0,-1),
        EAST(-1, 0),
        WEST(1, 0)
    }

    data class Point(val x: Int, val y: Int, val number: Int, var visited: Boolean = false) {
        fun adjacent(maxX: Int, maxY: Int): List<Pair<Int, Int>> =
            Direction.values().mapNotNull { direction ->
                val maybeX = x + direction.deltaX
                val maybeY = y + direction.deltaY
                if (maybeX in 0 until maxX && maybeY in 0 until maxY)
                    Pair(maybeX, maybeY)
                else
                    null
            }

        companion object {
            fun getIndexOfPair(p: Pair<Int, Int>, maxY: Int): Int = (p.first * maxY) + p.second
        }
    }

    private val maxX = input.size
    private val maxY = input.first().length


    private fun getLocalMinimums(points: List<Point>): List<Point> {
        return points.filter { point ->
            point.adjacent(maxX, maxY).all { adjacent ->
                points[Point.getIndexOfPair(adjacent, maxY)].number > point.number
            }
        }
    }

    private fun getDfsSum(points:List<Point>, x: Int, y: Int): Int {
        return if (x in 0 until maxX && y in 0 until maxY) {
            val point = points[Point.getIndexOfPair(Pair(x, y), maxY)]
            if (!point.visited && point.number != 9) {
                point.visited = true
                1 + Direction.values().sumOf { direction -> getDfsSum(points, x + direction.deltaX, y + direction.deltaY) }
            } else {
                0
            }
        } else {
            0
        }
    }

    override fun first(): String {
        val points = input.flatMapIndexed { x, row -> row.mapIndexed { y, col -> Point(x, y, col - '0') } }
        val riskLevel = getLocalMinimums(points).sumOf {
            it.number + 1
        }
        return riskLevel.toString()
    }

    override fun second(): String {
        val points = input.flatMapIndexed { x, row -> row.mapIndexed { y, col -> Point(x, y, col - '0') } }

        val basinSums = points.mapNotNull { (x, y, number, visited) ->
            if (visited || number == 9) null
            else getDfsSum(points, x, y)
        }

        return basinSums.sortedDescending().take(3).reduce(Int::times).toString()
    }

    override fun getLabel() = "2021/12/09 - Smoke Basin"
}
