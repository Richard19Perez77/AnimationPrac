package com.rperez.animationprac.presentation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent

@Composable
fun FourWayFlyOut() {
    var selectedPerson by remember { mutableStateOf<Person?>(null) }

    var positionStart by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var positionEnd by remember { mutableStateOf<LayoutCoordinates?>(null) }

    var startOffset by remember { mutableStateOf<Offset?>(null) }
    var endOffset by remember { mutableStateOf<Offset?>(null) }
    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    var displayText by remember { mutableStateOf("") }

    if (selectedPerson != null) {
        selectedPerson?.let { person ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red)
                    .clickable(onClick = { selectedPerson = null }),
            ) {
                // Animate only when both are attached and valid
                LaunchedEffect(startOffset, positionEnd) {
                    startOffset?.let { s ->
                        endOffset?.let { e ->
                            offset.snapTo(s)
                            displayText = person.name
                            offset.animateTo(e, animationSpec = tween(1000))
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = offset.value.x
                            translationY = offset.value.y
                        }
                ) {
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.onGloballyPositioned { coords ->
                            positionEnd = coords
                            positionEnd?.takeIf { it.isAttached }?.let {
                                endOffset = it.positionInParent()
                            }
                        }
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = people[0].name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .onGloballyPositioned { coords ->
                        positionStart = coords
                        positionStart?.takeIf { it.isAttached }?.let {
                            startOffset = it.positionInParent()
                        }
                    }
                    .clickable {
                        selectedPerson = people[0]
                    }
            )
        }
    }
}