package com.java1337.adventofcode2021

// You're minding your own business on a ship at sea when the Overboard alarm goes off! You rush to see if you can
// help. Apparently, one of the Elves tripped and accidentally sent the sleigh keys flying into the ocean!
//
// Before you know it, you're inside a submarine the Elves keep ready for situations like this. It's covered in
// Christmas lights (because of course it is), and it even has an experimental antenna that should be able to
// track the keys if you can boost its signal strength high enough; there's a little meter that indicates the
// antenna's signal strength by displaying 0-50 stars.
//
// Your instincts tell you that in order to save Christmas, you'll need to get all fifty stars by December 25th.
//
// Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar;
// the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
//
class App {

    companion object {

        private const val RESET = "\u001B[0m"
        private const val RED   = "\u001B[31m"
        private const val CYAN  = "\u001B[36m"

        @JvmStatic
        fun main(args: Array<String>) {
            val challenges = listOf(
                Day20211201.create(),
                Day20211202.create(),
                Day20211203.create(),
                Day20211204.create(),
                Day20211205.create(),
                Day20211206.create(),
                Day20211207.create(),
                Day20211208.create(),
            )
            for (challenge in challenges) {
                println()
                println("Running challenge $CYAN${challenge.getLabel()}$RESET")
                println("The first answer is $RED${challenge.first()}$RESET")
                println("The second answer is $RED${challenge.second()}$RESET")
            }
        }
    }
}
