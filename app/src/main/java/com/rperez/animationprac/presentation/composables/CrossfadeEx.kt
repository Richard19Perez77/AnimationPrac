package com.rperez.animationprac.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class MyColors(val color: Color) {
    Red(Color.Red), Green(Color.Green), Blue(Color.Blue)
}

@Composable
fun CrossfadeDemo() {
    var setToColor by remember { mutableStateOf(MyColors.Red) }
    var currentColor by remember { mutableStateOf(MyColors.Red) }
    Column {
        Row {
            MyColors.entries.forEach { color ->
                Button(
                    onClick = { setToColor = color },
                    Modifier
                        .weight(1f, true)
                        .height(48.dp)
                        .background(color.color),
                    colors = ButtonDefaults.buttonColors(color.color)
                ) {
                    Text(text = color.name)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = setToColor, animationSpec = tween(3000)) { currColor ->
                currentColor = currColor
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(currColor.color),
                    contentAlignment = Alignment.Center
                ) {}
            }
            Crossfade(targetState = setToColor, animationSpec = tween(1000)) { currColor ->
                currentColor = currColor
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    when (currColor) {
                        MyColors.Red -> Text(text = "RED")
                        MyColors.Green -> Text(text = "GREEN")
                        MyColors.Blue -> Text(text = "BLUE")
                    }
                }
            }
            Text(
                text = "currColor = ${currentColor.name} setToColor = ${setToColor.name}"
            )
        }
    }
}
