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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
        targetValue = 32f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float-elevation"
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
            modifier = Modifier.shadow(elevation = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "box 16.dp",
            )
        }

        Box(
            modifier = Modifier
                .shadow(elevation = animatedDp)
        ) {
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "animatedDp ${animatedDp.value}",
            )
        }

        Surface(
            modifier = Modifier.padding(8.dp).shadow(elevation = 8.dp),
            shadowElevation = 8.dp,
            color = Color.Red
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "surface 8",
            )
        }

        Surface(
            modifier = Modifier.padding(8.dp).shadow(elevation = 32.dp),
            shadowElevation = 32.dp,
            color = Color.Red
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "surface 32",
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(animatedDp),
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "animatedDp",
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "elevation 4",
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "elevation 8",
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(16.dp),
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "elevation 16",
            )
        }

        Card(
            elevation = CardDefaults.cardElevation(32.dp),
            modifier = Modifier
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "elevation 32",
            )
        }
    }
}
