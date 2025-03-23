package com.rperez.animationprac.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Snowflake(
    var x: Float,
    var y: Float,
    var radius: Float,
    var speed: Float
)

fun generateRandomSnowflake(canvasHeight: Float): Snowflake {
    return Snowflake(
        x = Random.nextFloat(),
        y = Random.nextFloat() * canvasHeight,
        radius = Random.nextFloat() * 2f + 2f, // Size between 2 and 4
        speed = Random.nextFloat() * 1.5f + 0.5f  // Speed between 0.5 and 2
    )
}

fun DrawScope.drawSnowflake(snowflake: Snowflake, height: Float) {
    drawCircle(
        color = Color.White,
        radius = snowflake.radius,
        center = Offset(snowflake.x * size.width, snowflake.y % height)
    )
}

@Composable
fun Snowfall() {
    var canvasHeight by remember { mutableFloatStateOf(0f) }

    var snowflakes by remember {
        mutableStateOf(List(100) { generateRandomSnowflake(canvasHeight) })
    }

    LaunchedEffect(Unit) {
        while (true) {
            snowflakes = snowflakes.map { flake ->
                val newY = flake.y + flake.speed
                if (newY > canvasHeight) {
                    flake.copy(x = Random.nextFloat(), y = 0f)
                } else {
                    flake.copy(y = newY)
                }
            }
            delay(16)
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        canvasHeight = size.height
        snowflakes.forEach { snowflake ->
            drawSnowflake(snowflake, canvasHeight)
        }
    }
}
