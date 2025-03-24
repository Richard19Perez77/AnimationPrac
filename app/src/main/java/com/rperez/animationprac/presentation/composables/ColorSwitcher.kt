package com.rperez.animationprac.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ColorSwitcher() {
    var isDark by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { isDark = !isDark }) {
            Text(if (isDark) "Switch to Light" else "Switch to Dark")
        }

        Crossfade(targetState = isDark, animationSpec = tween(1000)) { dark ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (dark) Color.Black else Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (dark) "Dark Theme" else "Light Theme",
                    color = if (dark) Color.White else Color.Black
                )
            }
        }
    }
}
