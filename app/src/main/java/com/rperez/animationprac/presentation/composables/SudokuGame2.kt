package com.rperez.animationprac.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class SudokuViewModel2 : ViewModel() {
    private val _board = mutableStateOf(Array(9) { IntArray(9) { 0 } })
    val board: State<Array<IntArray>> = _board

    private val _selectedCell = mutableStateOf(Pair(-1, -1))
    val selectedCell: State<Pair<Int, Int>> = _selectedCell

    private val _isSolved = mutableStateOf(false)
    val isSolved: State<Boolean> = _isSolved

    init {
        generateNewGame()
    }

    fun generateNewGame() {
        _board.value = Array(9) { IntArray(9) { 0 } }
        _isSolved.value = false

        solveSudoku()
        removeNumbers(40)
    }

    fun solveSudoku(): Boolean {
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (_board.value[row][col] == 0) {
                    for (num in 1..9) {
                        if (isValid(row, col, num)) {
                            _board.value[row][col] = num
                            if (solveSudoku()) {
                                return true
                            }
                            _board.value[row][col] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    fun isValid(row: Int, col: Int, num: Int): Boolean {
        for (x in 0 until 9) {
            if (_board.value[row][x] == num) return false
        }
        for (x in 0 until 9) {
            if (_board.value[x][col] == num) return false
        }
        val boxRow = row - row % 3
        val boxCol = col - col % 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (_board.value[boxRow + i][boxCol + j] == num) return false
            }
        }
        return true
    }

    fun removeNumbers(count: Int) {
        var removed = 0
        while (removed < count) {
            val row = (0 until 9).random()
            val col = (0 until 9).random()
            if (_board.value[row][col] != 0) {
                _board.value[row][col] = 0
                removed++
            }
        }
    }

    fun selectCell(row: Int, col: Int) {
        _selectedCell.value = Pair(row, col)
    }

    fun setCellValue(value: Int) {
        val (row, col) = _selectedCell.value
        if (row in 0..8 && col in 0..8) {
            _board.value[row][col] = value
            checkSolved()
        }
    }

    fun checkSolved() {
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (_board.value[row][col] == 0) return
                if (!isValid(row, col, _board.value[row][col])) return
            }
        }
        _isSolved.value = true
    }
}

@Composable
fun SudokuGame2(viewModel: SudokuViewModel2 = viewModel()) {
    val board by viewModel.board
    val selectedCell by viewModel.selectedCell
    val isSolved by viewModel.isSolved

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "Sudoku",
            style = MaterialTheme.typography.headlineLarge
        )

        SudokuBoard(
            board = board,
            selectedCell = selectedCell,
            onCellSelected = viewModel::selectCell
        )

        Spacer(modifier = Modifier.height(16.dp))

        NumberPad2(
            onNumberSelected = viewModel::setCellValue
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.generateNewGame() }
        ) {
            Text("New Game")
        }

        if (isSolved) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Congratulations! You solved it!",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun SudokuBoard(
    board: Array<IntArray>,
    selectedCell: Pair<Int, Int>,
    onCellSelected: (Int, Int) -> Unit,
) {
    val cellSize = 40.dp
    Column(
        modifier = Modifier
            .border(2.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        for (row in 0 until 9) {
            Row {
                for (col in 0 until 9) {
                    val isSelected = selectedCell.first == row && selectedCell.second == col
                    val backgroundColor = if (isSelected) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }

                    val borderWidths = BorderStroke(
                        width = when {
                            row == 0 && col == 0 -> 2.dp // Top-left corner
                            row == 0 -> 1.dp // Top edge
                            col == 0 -> 1.dp // Left edge
                            row % 3 == 0 -> 1.dp // Top of 3x3 block
                            col % 3 == 0 -> 1.dp // Left of 3x3 block
                            else -> 0.5.dp // Internal borders
                        },
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Box(
                        modifier = Modifier
                            .size(cellSize)
                            .background(backgroundColor)
                            .border(borderWidths)
                            .clickable { onCellSelected(row, col) },
                        contentAlignment = Alignment.Center
                    ) {
                        if (board[row][col] != 0) {
                            Text(
                                text = board[row][col].toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NumberPad2(onNumberSelected: (Int) -> Unit) {
    Column {
        Row {
            for (num in 1..5) {
                NumberButton(
                    num,
                    onNumberSelected
                )
            }
        }
        Row {
            for (num in 6..9) {
                NumberButton(
                    num,
                    onNumberSelected
                )
                NumberButton(0, onNumberSelected)
            }
        }
    }
}

@Composable
fun NumberButton(number: Int, onClick: (Int) -> Unit) {
    Button(
        onClick = { onClick(number) },
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp)
    ) {
        Text(
            text = if (number == 0) "X" else number.toString(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}