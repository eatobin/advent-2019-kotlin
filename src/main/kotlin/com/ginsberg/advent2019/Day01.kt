/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 1 - The Tyranny of the Rocket Equation
 * Problem Description: http://adventofcode.com/2019/day/1
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day1/
 */
package com.ginsberg.advent2019

class Day01(input: List<String>) {

    private val modules: List<Int> = input.map { it.toInt() }

    fun solvePart1(): Int = modules.sumOf { it.fuel() }

    fun solvePart2(): Int = modules.sumOf { it.fuelWithFuel() }

    fun solvePart2EAT(): Int = modules.sumOf { it.ericFuel() }

    private fun Int.fuel(): Int = (this / 3) - 2

    private fun Int.fuelWithFuel(): Int = if (this < 9) {
        0
    } else {
        val fuel = this.fuel()
        fuel + fuel.fuelWithFuel()
    }

    private fun Int.ericFuel(): Int {
        tailrec fun fuelAccumulator(mass: Int, accum: Int): Int {
            val newFuel: Int = mass.fuel()
            return if (newFuel > 0) {
                fuelAccumulator(mass = newFuel, accum = accum + newFuel)
            } else {
                accum
            }
        }
        return fuelAccumulator(mass = this, accum = 0)
    }
}
