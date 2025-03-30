package com.rperez.animationprac.util

import java.util.Locale
import kotlin.system.measureNanoTime

class SudokuUtil1 {
    private val board = Array(9) { IntArray(9) { 0 } }

    private fun isSafe(board: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        for (i in 0..8) {
            if (board[row][i] == num || board[i][col] == num) return false
        }
        val startRow = row / 3 * 3
        val startCol = col / 3 * 3
        for (r in 0..2)
            for (c in 0..2)
                if (board[startRow + r][startCol + c] == num) return false
        return true
    }

    private fun fillBoard(board: Array<IntArray>): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == 0) {
                    val numbers = (1..9).shuffled()
                    for (num in numbers) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num
                            if (fillBoard(board)) return true
                            board[row][col] = 0 // backtrack
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    init {
        fillBoard(board)
    }
}

fun main() {
    val times2 = (1..500).map {
        measureNanoTime {
            SudokuUtil2()
        }
    }

    val avg2 = times2.average()
    val max2 = times2.maxOrNull()
    val min2 = times2.minOrNull()

    val times1 = (1..500).map {
        measureNanoTime {
            SudokuUtil1()
        }
    }

    val avg1 = times1.average()
    val max1 = times1.maxOrNull()
    val min1 = times1.minOrNull()

    println("+------------+----------------------+----------------------+----------------------+")
    println("| Algorithm  |       Average (ns)   |         Min (ns)     |         Max (ns)     |")
    println("+------------+----------------------+----------------------+----------------------+")
    println(String.format(Locale.getDefault(), "| %-10s | %20.2f | %20d | %20d |", "Sudoku2", avg2, min2, max2))
    println(String.format(Locale.getDefault(), "| %-10s | %20.2f | %20d | %20d |", "Sudoku1", avg1, min1, max1))
    println("+------------+----------------------+----------------------+----------------------+")
}
