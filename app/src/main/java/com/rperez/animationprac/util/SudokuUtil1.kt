package com.rperez.animationprac.util


class SudokuUtil1 {
    private val board = Array(9) { IntArray(9) { 0 } }
    private var fillBoardCounter = 0
    private var backtrackCounter = 0

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
        fillBoardCounter++
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == 0) {
                    val numbers = (1..9).shuffled()
                    for (num in numbers) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num
                            if (fillBoard(board)) return true
                            board[row][col] = 0 // backtrack
                            backtrackCounter++
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
        println("fillBoardCounter $fillBoardCounter")
        println("backtrackCounter $backtrackCounter")
        board.forEach { println(it.joinToString()) }
    }
}

fun main() {
    SudokuUtil1()
}
