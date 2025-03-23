package com.rperez.animationprac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.rperez.animationprac.presentation.composables.FadingBox
import com.rperez.animationprac.ui.theme.AnimationPracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationPracTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    FadingBox()
                }
            }
        }
    }
}