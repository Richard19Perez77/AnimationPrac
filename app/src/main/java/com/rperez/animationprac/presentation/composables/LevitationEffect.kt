package com.rperez.animationprac.presentation.composables

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LevitationEffect() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite-elevation")

    val animatedFloat by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 16f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        ),
    )

    val animatedDp = animatedFloat.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .shadow(elevation = animatedDp)
        ) {
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
                    .width(200.dp),
                text = "box ${animatedDp.value}",
            )
        }

        Surface(
            shadowElevation = animatedDp,
            modifier = Modifier
                .padding(32.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp),
                text = "surface",
                color = Color.Black
            )
        }

        Text(
            modifier = Modifier
                .shadow(animatedDp)
                .background(Color.White)
                .padding(8.dp),
            text = "text",
        )
    }
}
