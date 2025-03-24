package com.rperez.animationprac.presentation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import kotlinx.coroutines.launch

@Composable
fun FourWayFlyOut() {
    var selectedPerson by remember { mutableStateOf<Person?>(null) }

    var positionStart by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var positionEnd by remember { mutableStateOf<LayoutCoordinates?>(null) }

    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    val scale = remember { Animatable(1f) }

    val startFontSize = MaterialTheme.typography.bodyLarge.fontSize.value
    val endFontSize = MaterialTheme.typography.headlineMedium.fontSize.value

    if (selectedPerson != null) {
        selectedPerson?.let { person ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { selectedPerson = null }),
            ) {
                // Animate only when both are attached and valid
                LaunchedEffect(positionStart, positionEnd) {
                    val startAttached = positionStart?.isAttached == true
                    val endAttached = positionEnd?.isAttached == true

                    if (startAttached && endAttached) {
                        val startOffset = positionStart!!.localToWindow(Offset.Zero)
                        val endOffset = positionEnd!!.localToWindow(Offset.Zero)

                        offset.snapTo(startOffset)
                        scale.snapTo(startFontSize)

                        launch {
                            offset.animateTo(
                                targetValue = endOffset,
                                animationSpec = tween(
                                    durationMillis = 2000,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }
                        launch {
                            scale.animateTo(
                                targetValue = endFontSize,
                                animationSpec = tween(
                                    durationMillis = 2000,
                                    easing = FastOutSlowInEasing
                                )

                            )
                        }
                    }
                }

                // Animated target
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = offset.value.x
                            translationY = offset.value.y
                            scaleX = scale.value
                            scaleY = scale.value
                        }
                ) {
                    Text(
                        text = person.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.onGloballyPositioned {
                            positionEnd = it
                        }
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            people.forEachIndexed { index, person ->
                Text(
                    text = person.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            if (selectedPerson == null && index == 0) {
                                positionStart = coordinates
                            }
                        }
                        .clickable {
                            selectedPerson = person
                        }
                )
            }
        }
    }
}
