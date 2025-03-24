package com.rperez.animationprac.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SimonGame(viewModel: SimonViewModel = viewModel()) {
    val state = viewModel.state.value

    if (state.isGameOver) {
        Column {
            Button(
                onClick = { viewModel.startGame() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Score: ${viewModel.state.value.sequence.size - 1}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Restart?")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    } else {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = { viewModel.startGame() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Restart",
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(5f)
            )
            Box(
                modifier = Modifier.weight(10f)
            ) {
                Grid(viewModel, state)
            }
        }
    }
}

data class SimonState(
    val sequence: List<Int> = emptyList(),
    val userInput: List<Int> = emptyList(),
    val isUserTurn: Boolean = false,
    val isGameOver: Boolean = false,
    val flashingIndex: Int? = null
)

val simonColors = listOf(Color(0xff3edd4b), Color(0xff4b3edd), Color(0xffdd4b3e), Color(0xffffea37))

class SimonViewModel : ViewModel() {
    private val _state = mutableStateOf(SimonState())
    val state: State<SimonState> get() = _state

    fun startGame() {
        _state.value = SimonState()
        nextRound()
    }

    fun nextRound() {
        val next = (0..3).random()
        _state.value = _state.value.copy(
            sequence = _state.value.sequence + next,
            userInput = emptyList(),
            isUserTurn = false
        )
        playSequence()
    }

    private fun playSequence() {
        viewModelScope.launch {
            _state.value.sequence.forEachIndexed { index, colorIndex ->
                _state.value = _state.value.copy(flashingIndex = colorIndex)
                delay(500)
                _state.value = _state.value.copy(flashingIndex = null)
                delay(250)
            }
            _state.value = _state.value.copy(isUserTurn = true)
        }
    }

    fun onColorTap(index: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(flashingIndex = index)
            delay(100)
            _state.value = _state.value.copy(flashingIndex = null)
        }

        if (!_state.value.isUserTurn) return

        val updatedInput = _state.value.userInput + index
        val correct = _state.value.sequence.take(updatedInput.size)

        if (correct == updatedInput) {
            if (updatedInput.size == _state.value.sequence.size) {
                _state.value = _state.value.copy(userInput = updatedInput, isUserTurn = false)
                viewModelScope.launch {
                    delay(1000)
                    nextRound()
                }
            } else {
                _state.value = _state.value.copy(userInput = updatedInput)
            }
        } else {
            _state.value = _state.value.copy(isGameOver = true)
        }
    }
}

@Composable
fun Grid(viewModel: SimonViewModel, state: SimonState) {
    Column {
        for (row in 0..1) {
            Row(modifier = Modifier.weight(1f)) {
                for (col in 0..1) {
                    val index = row * 2 + col
                    val color = simonColors[index]
                    val flashing = state.flashingIndex == index
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .padding(4.dp)
                            .background(
                                if (flashing) color.copy(alpha = .25f) else color,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable(enabled = state.isUserTurn) {
                                viewModel.onColorTap(index)
                            }
                    )
                }
            }
        }
    }
}

val gradientBrush = Brush.radialGradient(
    colors = simonColors,
    center = Offset(0.5f, 0.5f), // Center of the canvas
    radius = 500f // Adjust to fit your layout
)