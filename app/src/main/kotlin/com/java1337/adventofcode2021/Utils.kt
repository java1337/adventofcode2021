package com.java1337.adventofcode2021

class Utils {
    companion object {
        fun readFileAsListOfString(file: String): List<String> {
            @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            val inputRaw = Utils::class.java.classLoader.getResource(file).readText()
            return inputRaw.split("\n")
        }
    }
}