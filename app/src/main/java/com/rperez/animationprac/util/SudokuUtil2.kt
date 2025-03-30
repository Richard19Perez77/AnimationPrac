package com.rperez.animationprac.util

import kotlin.random.Random

/**
 * Sudoku util2
 *
 *  create a valid grid by using a full note list of valid values
 *  once a valid value is set it's the only value in it
 *  could join this to result with flattening values
 *  after each valid value is found remove the value from other sets
 *  sets include grid, row and column
 *  at the end should have a valid sudoku puzzle array of size 81 for result list to return
 *  might have to check for empty grid values from constant removals from previous grid row and column values
 *
 * @constructor Create empty Sudoku util2
 */
class SudokuUtil2 {

    private var grids = listOf(
        listOf(0, 1, 2, 9, 10, 11, 18, 19, 20),
        listOf(3, 4, 5, 12, 13, 14, 21, 22, 23),
        listOf(6, 7, 8, 15, 16, 17, 24, 25, 26),
        listOf(27, 28, 29, 36, 37, 38, 45, 46, 47),
        listOf(30, 31, 32, 39, 40, 41, 48, 49, 50),
        listOf(33, 34, 35, 42, 43, 44, 51, 52, 53),
        listOf(54, 55, 56, 63, 64, 65, 72, 73, 74),
        listOf(57, 58, 59, 66, 67, 68, 75, 76, 77),
        listOf(60, 61, 62, 69, 70, 71, 78, 79, 80),
    )

    private var cols = listOf(
        listOf(0, 9, 18, 27, 36, 45, 54, 63, 72),
        listOf(1, 10, 19, 28, 37, 46, 55, 64, 73),
        listOf(2, 11, 20, 29, 38, 47, 56, 65, 74),
        listOf(3, 12, 21, 30, 39, 48, 57, 66, 75),
        listOf(4, 13, 22, 31, 40, 49, 58, 67, 76),
        listOf(5, 14, 23, 32, 41, 50, 59, 68, 77),
        listOf(6, 15, 24, 33, 42, 51, 60, 69, 78),
        listOf(7, 16, 25, 34, 43, 52, 61, 70, 79),
        listOf(8, 17, 26, 35, 44, 53, 62, 71, 80),
    )

    private val rows = listOf(
        listOf(0, 1, 2, 3, 4, 5, 6, 7, 8),
        listOf(9, 10, 11, 12, 13, 14, 15, 16, 17),
        listOf(18, 19, 20, 21, 22, 23, 24, 25, 26),
        listOf(27, 28, 29, 30, 31, 32, 33, 34, 35),
        listOf(36, 37, 38, 39, 40, 41, 42, 43, 44),
        listOf(45, 46, 47, 48, 49, 50, 51, 52, 53),
        listOf(54, 55, 56, 57, 58, 59, 60, 61, 62),
        listOf(63, 64, 65, 66, 67, 68, 69, 70, 71),
        listOf(72, 73, 74, 75, 76, 77, 78, 79, 80)
    )

    // each index should have a list of ints
    private var gridValues = Array(81) {
        mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    private var index = 0
    private var row = 0
    private var col = 0
    private var grid = 0
    private var itemValue = 0
    private var seed = 0

    init {
        var created = createValues(row, col)
        var tries = 0
        while (!created) {
            tries++
            index = 0
            row = 0
            col = 0
            grid = 0
            itemValue = 0
            seed = 0
            gridValues = Array(81) {
                mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
            }
            created = createValues(row, col)
        }
        println("tries $tries")
        val values: List<Int> = gridValues.flatMap { it }
        println("values count ${values.count()}")
        println("values $values")

        val results = mutableListOf<List<Int>>()
        grids.forEach { grid ->
            val temp = mutableListOf<Int>()
            grid.forEach {
                temp.add(values[it])
            }
            results.add(temp)
        }

        rows.forEach { row ->
            val temp = mutableListOf<Int>()
            row.forEach {
                temp.add(values[it])
            }
            results.add(temp)
        }

        cols.forEach { col ->
            val temp = mutableListOf<Int>()
            col.forEach {
                temp.add(values[it])
            }
            results.add(temp)
        }

        results.forEach {
            val sorted = it.toMutableList().sorted()
            sorted.forEachIndexed { i, v ->
                if (i != v - 1) {
                    println("invalid: $i, $v")
                } else {
                    println("valid: $i, $v")
                }
            }
        }
    }

    private fun createValues(row1: Int, col1: Int): Boolean {
        println("$row1, $col1")

        // don't need index if we have row and column
        index = rows[row1][col1]
        println("index $index")
        grid = grids.indexOfFirst { it.contains(index) }
        println("grid $grid")

        // get random value from list of available left
        if (gridValues[index].isEmpty()) {
            println("empty list at $row1, $col1")
            return false
        }

        seed = Random.nextInt(gridValues[index].size)
        itemValue = gridValues[index][seed]

        // remove it from the grid lists
        grids[grid].forEach {
            gridValues[it].remove(itemValue)
        }

        // remove it from the row lists
        rows[row1].forEach {
            gridValues[it].remove(itemValue)
        }

        // remove it from the colum lists
        cols[col1].forEach {
            gridValues[it].remove(itemValue)
        }

        // should be only item in its own index
        gridValues[index].clear()
        gridValues[index].add(itemValue)

        if (index == 80) {
            return true
        }

        col++
        if (col > 8) {
            col = 0
            row++
        }
        return createValues(row, col)
    }
}

fun main() {
    SudokuUtil2()
}