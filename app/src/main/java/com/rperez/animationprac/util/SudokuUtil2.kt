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
 *  might have to check for empty gridvalues from constant removals from previous grid row and column values
 *
 * @constructor Create empty Sudoku util2
 */
class SudokuUtil2 {

    var grids = listOf<List<Int>>(
        listOf(0, 1, 2, 9, 10, 11, 18, 19, 20),
        listOf(3, 4, 5, 12, 13, 14, 21, 22, 23),
        listOf(6, 7, 8, 15, 16, 17, 24, 26, 26),
        listOf(27, 28, 29, 36, 37, 38, 45, 46, 47),
        listOf(30, 31, 32, 39, 40, 41, 48, 49, 50),
        listOf(33, 34, 35, 42, 43, 44, 51, 52, 53),
        listOf(54, 55, 56, 63, 64, 65, 72, 73, 74),
        listOf(57, 58, 59, 66, 67, 68, 75, 76, 77),
        listOf(60, 61, 62, 69, 70, 71, 78, 79, 80),
    )

    var cols = listOf<List<Int>>(
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

    val rows = listOf<List<Int>>(
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
    var gridValues = Array<MutableList<Int>>(81) {
        mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    var index = 23
    var row = 0
    var col = 0

    init {
        getItem(row,col)
        get0Item()
    }

    // row, col

    // r0 c0 g0
    private fun get0Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[0].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[0].clear()
        gridValues[0].add(itemValue)

        // remove from grid
        gridValues[1].remove(itemValue)
        gridValues[2].remove(itemValue)
        gridValues[9].remove(itemValue)
        gridValues[10].remove(itemValue)
        gridValues[11].remove(itemValue)
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[27].remove(itemValue)
        gridValues[36].remove(itemValue)
        gridValues[45].remove(itemValue)
        gridValues[54].remove(itemValue)
        gridValues[63].remove(itemValue)
        gridValues[72].remove(itemValue)

        // remove from row
        gridValues[3].remove(itemValue)
        gridValues[4].remove(itemValue)
        gridValues[5].remove(itemValue)
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // r0 c1 g0
    private fun get1Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[1].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[1].clear()
        gridValues[1].add(itemValue)

        // remove from rest of grid
        gridValues[2].remove(itemValue)
        gridValues[9].remove(itemValue)
        gridValues[10].remove(itemValue)
        gridValues[11].remove(itemValue)
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[28].remove(itemValue)
        gridValues[37].remove(itemValue)
        gridValues[46].remove(itemValue)
        gridValues[55].remove(itemValue)
        gridValues[64].remove(itemValue)
        gridValues[73].remove(itemValue)

        // remove from row
        gridValues[3].remove(itemValue)
        gridValues[4].remove(itemValue)
        gridValues[5].remove(itemValue)
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // r0 c2 g0
    private fun get2Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[2].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[2].clear()
        gridValues[2].add(itemValue)

        // remove from rest of grid
        gridValues[9].remove(itemValue)
        gridValues[10].remove(itemValue)
        gridValues[11].remove(itemValue)
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[29].remove(itemValue)
        gridValues[38].remove(itemValue)
        gridValues[47].remove(itemValue)
        gridValues[56].remove(itemValue)
        gridValues[65].remove(itemValue)
        gridValues[74].remove(itemValue)

        // remove from row
        gridValues[3].remove(itemValue)
        gridValues[4].remove(itemValue)
        gridValues[5].remove(itemValue)
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // r0 c3 g1
    private fun get3Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[3].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[3].clear()
        gridValues[3].add(itemValue)

        // remove from rest of grid
        gridValues[4].remove(itemValue)
        gridValues[5].remove(itemValue)
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[30].remove(itemValue)
        gridValues[39].remove(itemValue)
        gridValues[48].remove(itemValue)
        gridValues[57].remove(itemValue)
        gridValues[66].remove(itemValue)
        gridValues[75].remove(itemValue)

        // remove from row
        gridValues[4].remove(itemValue)
        gridValues[5].remove(itemValue)
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // r0 c4 g1
    private fun get4Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[4].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[4].clear()
        gridValues[4].add(itemValue)

        // remove from rest of grid
        gridValues[5].remove(itemValue)
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[31].remove(itemValue)
        gridValues[40].remove(itemValue)
        gridValues[49].remove(itemValue)
        gridValues[58].remove(itemValue)
        gridValues[67].remove(itemValue)
        gridValues[76].remove(itemValue)

        // remove from row
        gridValues[5].remove(itemValue)
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // r0 c5 g1
    private fun get5Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[5].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[5].clear()
        gridValues[5].add(itemValue)

        // remove from rest of grid
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[32].remove(itemValue)
        gridValues[41].remove(itemValue)
        gridValues[50].remove(itemValue)
        gridValues[59].remove(itemValue)
        gridValues[68].remove(itemValue)
        gridValues[77].remove(itemValue)

        // remove from row
        gridValues[6].remove(itemValue)
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // 0, 6 grid 2
    private fun get6Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[6].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[6].clear()
        gridValues[6].add(itemValue)

        // remove from rest of grid
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[33].remove(itemValue)
        gridValues[42].remove(itemValue)
        gridValues[51].remove(itemValue)
        gridValues[60].remove(itemValue)
        gridValues[69].remove(itemValue)
        gridValues[78].remove(itemValue)

        // remove from row
        gridValues[7].remove(itemValue)
        gridValues[8].remove(itemValue)
    }

    // 0, 7 grid 2
    private fun get7Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[7].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[7].clear()
        gridValues[7].add(itemValue)

        // remove from rest of grid
        gridValues[8].remove(itemValue)
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[34].remove(itemValue)
        gridValues[43].remove(itemValue)
        gridValues[52].remove(itemValue)
        gridValues[61].remove(itemValue)
        gridValues[70].remove(itemValue)
        gridValues[79].remove(itemValue)

        // remove from row
        gridValues[8].remove(itemValue)
    }

    // 0, 8 grid 2
    private fun get8Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[8].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[8].clear()
        gridValues[8].add(itemValue)

        // remove from rest of grid
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[35].remove(itemValue)
        gridValues[44].remove(itemValue)
        gridValues[53].remove(itemValue)
        gridValues[62].remove(itemValue)
        gridValues[71].remove(itemValue)
        gridValues[80].remove(itemValue)
    }

    // 1, 0 grid 0
    private fun get9Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[9].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[9].clear()
        gridValues[9].add(itemValue)

        // remove from grid
        gridValues[10].remove(itemValue)
        gridValues[11].remove(itemValue)
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[27].remove(itemValue)
        gridValues[36].remove(itemValue)
        gridValues[45].remove(itemValue)
        gridValues[54].remove(itemValue)
        gridValues[63].remove(itemValue)
        gridValues[72].remove(itemValue)

        // remove from row
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    // 1, 1 grid 0
    private fun get10Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[10].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[10].clear()
        gridValues[10].add(itemValue)

        // remove from grid
        gridValues[11].remove(itemValue)
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[28].remove(itemValue)
        gridValues[37].remove(itemValue)
        gridValues[46].remove(itemValue)
        gridValues[55].remove(itemValue)
        gridValues[64].remove(itemValue)
        gridValues[73].remove(itemValue)

        // remove from row
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    // 1, 2 grid 0
    private fun get11Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[11].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[11].clear()
        gridValues[11].add(itemValue)

        // remove from grid
        gridValues[18].remove(itemValue)
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[29].remove(itemValue)
        gridValues[38].remove(itemValue)
        gridValues[47].remove(itemValue)
        gridValues[56].remove(itemValue)
        gridValues[65].remove(itemValue)
        gridValues[74].remove(itemValue)

        // remove from row
        gridValues[12].remove(itemValue)
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    //1, 3 grid 1
    private fun get12Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[12].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[12].clear()
        gridValues[12].add(itemValue)

        // remove from grid
        gridValues[13].remove(itemValue)
        gridValues[14].remove(itemValue)
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[30].remove(itemValue)
        gridValues[39].remove(itemValue)
        gridValues[48].remove(itemValue)
        gridValues[57].remove(itemValue)
        gridValues[66].remove(itemValue)
        gridValues[75].remove(itemValue)

        // remove from row
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    // 1, 4 grid 1
    private fun get13Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[13].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[13].clear()
        gridValues[13].add(itemValue)

        // remove from grid
        gridValues[14].remove(itemValue)
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[31].remove(itemValue)
        gridValues[40].remove(itemValue)
        gridValues[49].remove(itemValue)
        gridValues[58].remove(itemValue)
        gridValues[67].remove(itemValue)
        gridValues[76].remove(itemValue)

        // remove from row
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    // 1, 5 grid 1
    private fun get14Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[14].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[14].clear()
        gridValues[14].add(itemValue)

        // remove from grid
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)

        // remove from column
        gridValues[32].remove(itemValue)
        gridValues[41].remove(itemValue)
        gridValues[50].remove(itemValue)
        gridValues[59].remove(itemValue)
        gridValues[68].remove(itemValue)
        gridValues[77].remove(itemValue)

        // remove from row
        gridValues[15].remove(itemValue)
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
    }

    // 1, 6 grid 2
    private fun get15Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[15].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[15].clear()
        gridValues[15].add(itemValue)

        // remove from rest of grid
        gridValues[16].remove(itemValue)
        gridValues[17].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[33].remove(itemValue)
        gridValues[42].remove(itemValue)
        gridValues[51].remove(itemValue)
        gridValues[60].remove(itemValue)
        gridValues[69].remove(itemValue)
        gridValues[78].remove(itemValue)
    }

    // 1, 7 grid 2
    private fun get16Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[16].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[16].clear()
        gridValues[16].add(itemValue)

        // remove from rest of grid
        gridValues[17].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[34].remove(itemValue)
        gridValues[43].remove(itemValue)
        gridValues[52].remove(itemValue)
        gridValues[61].remove(itemValue)
        gridValues[70].remove(itemValue)
        gridValues[79].remove(itemValue)
    }

    // 1, 8 grid 2
    private fun get17Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[17].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[17].clear()
        gridValues[17].add(itemValue)

        // remove from rest of grid
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)

        // remove from column
        gridValues[35].remove(itemValue)
        gridValues[44].remove(itemValue)
        gridValues[53].remove(itemValue)
        gridValues[62].remove(itemValue)
        gridValues[71].remove(itemValue)
        gridValues[80].remove(itemValue)
    }

    // 2, 0 grid 0
    private fun get18Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[18].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[18].clear()
        gridValues[18].add(itemValue)

        // remove from grid
        gridValues[19].remove(itemValue)
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[27].remove(itemValue)
        gridValues[36].remove(itemValue)
        gridValues[45].remove(itemValue)
        gridValues[54].remove(itemValue)
        gridValues[63].remove(itemValue)
        gridValues[72].remove(itemValue)

        // remove from row
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)
    }

    // 2, 1 grid 0
    private fun get19Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[19].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[19].clear()
        gridValues[19].add(itemValue)

        // remove from grid
        gridValues[20].remove(itemValue)

        // remove from column
        gridValues[28].remove(itemValue)
        gridValues[37].remove(itemValue)
        gridValues[46].remove(itemValue)
        gridValues[55].remove(itemValue)
        gridValues[64].remove(itemValue)
        gridValues[73].remove(itemValue)

        // remove from row
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)
    }

    // 2, 2 grid 0
    private fun get20Item() {
        // get random int from base list
        var itemValue = Random.nextInt(0, gridValues[20].size)
        // add item value to the index
        // remove from invalid indexes available lists
        gridValues[20].clear()
        gridValues[20].add(itemValue)

        // remove from column
        gridValues[29].remove(itemValue)
        gridValues[38].remove(itemValue)
        gridValues[47].remove(itemValue)
        gridValues[56].remove(itemValue)
        gridValues[65].remove(itemValue)
        gridValues[74].remove(itemValue)

        // remove from row
        gridValues[21].remove(itemValue)
        gridValues[22].remove(itemValue)
        gridValues[23].remove(itemValue)
        gridValues[24].remove(itemValue)
        gridValues[25].remove(itemValue)
        gridValues[26].remove(itemValue)
    }

    // r2, c3 g1
    private fun get21Item() {
        // get random value from list of available left
        var itemValue = Random.nextInt(0, gridValues[21].size)

        // remove it from the grid lists
        grids[1].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the row lists
        rows[2].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the colum lists
        cols[3].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // should be only item in its own index
        gridValues[21].filter { it == itemValue }
    }

    // r2, c4 g1
    private fun get22Item() {
        // get random value from list of available left
        var itemValue = Random.nextInt(0, gridValues[22].size)

        // remove it from the grid lists
        grids[1].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the row lists
        rows[2].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the colum lists
        cols[4].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // should be only item in its own index
        gridValues[22].filter { it == itemValue }
    }

    // r2, c5 g1
    private fun get23Item() {
        index++
        row = 2
        col = 5
        var grid = 1

        // get random value from list of available left
        var itemValue = Random.nextInt(0, gridValues[index].size)

        // remove it from the grid lists
        grids[1].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the row lists
        rows[2].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the colum lists
        cols[5].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // should be only item in its own index
        gridValues[index].filter { it == itemValue }

        col++
        if (col > 8) {
            col = 0
            row++
            grid = grids.indexOfFirst { it.contains(index) }
        }
        getItem(row, col)
    }

    fun getItem(row1: Int, col1: Int) {

        // don't need index if we have row and column
        var index2 = rows[row1][col1]
        var grid = grids.indexOfFirst { it.contains(index) }

        // get random value from list of available left
        var itemValue = Random.nextInt(0, gridValues[index2].size)

        // remove it from the grid lists
        grids[grid].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the row lists
        rows[row1].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // remove it from the colum lists
        cols[col1].forEach { it ->
            if (it != itemValue) gridValues[it].remove(itemValue)
        }

        // should be only item in its own index
        gridValues[index2].filter { it == itemValue }

        if (index2 == 80) return

        col++
        if (col > 8) {
            col = 0
            row++
        }
        getItem(row, col)
    }
}
