package com.rperez.animationprac.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class SudokuCell(
    val row: Int, val col: Int, val value: Int = 0, val isEditable: Boolean = true
)

class SudokuViewModel : ViewModel() {
    var grid by mutableStateOf(generateInitialGrid())
        private set

    var selectedCell: Pair<Int, Int>? by mutableStateOf(null)

    fun selectCell(row: Int, col: Int) {
        selectedCell = row to col
    }

    fun enterNumber(number: Int) {
        selectedCell?.let { (row, col) ->
            grid = grid.map { rowList ->
                rowList.map {
                    if (
                        it.row == row
                        && it.col == col
                        && it.isEditable
                    ) {
                        it.copy(value = number)
                    } else {
                        it
                    }
                }
            }
        }
    }

    private fun generateInitialGrid(): List<List<SudokuCell>> {
        return List(9) { row ->
            List(9) { col ->
                SudokuCell(row, col)
            }
        }
    }
}

@Composable
fun SudokuGame1(viewModel: SudokuViewModel = viewModel()) {
    Column {
        for (row in viewModel.grid) {
            Row {
                for (cell in row) {
                    SudokuCellView(
                        cell,
                        isSelected = viewModel.selectedCell == cell.row to cell.col
                    ) {
                        viewModel.selectCell(cell.row, cell.col)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        NumberPad(onNumberClick = viewModel::enterNumber)
    }
}

@Composable
fun NumberPad(onNumberClick: (Int) -> Unit) {
    Column {
        for (row in listOf(1..3, 4..6, 7..9)) {
            Row {
                for (num in row) {
                    Button(
                        onClick = { onNumberClick(num) },
                        modifier = Modifier
                            .size(48.dp)
                            .padding(4.dp)
                    ) {
                        Text("$num")
                    }
                }
            }
        }
    }
}

@Composable
fun SudokuCellView(cell: SudokuCell, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, if (isSelected) Color.Blue else Color.Gray)
            .background(if (!cell.isEditable) Color.LightGray else Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (cell.value != 0) cell.value.toString() else "",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}