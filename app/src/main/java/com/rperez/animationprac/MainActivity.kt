package com.rperez.animationprac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rperez.animationprac.presentation.composables.CrossfadeDemo
import com.rperez.animationprac.presentation.composables.FadingBox
import com.rperez.animationprac.presentation.composables.LevitationEffect
import com.rperez.animationprac.presentation.composables.SlideFadeAnimation
import com.rperez.animationprac.presentation.composables.Snowfall
import com.rperez.animationprac.ui.theme.AnimationPracTheme
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalUuidApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationPracTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val height = LocalConfiguration.current.screenHeightDp
                    LazyColumn {
                        item(key = 1) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height.dp)
                            ) {
                                FadingBox()
                            }
                        }
                        item(key = 2) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height.dp)
                            ) {
                                CrossfadeDemo()
                            }
                        }
                        item(key = 3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height.dp)
                            ) {
                                SlideFadeAnimation()
                            }
                        }
                        item(key = 4) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                LevitationEffect()
                            }
                        }
                        item(key = 5) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Snowfall()
                            }
                        }
                        item(key = 6) {
                            val people = remember {
                                listOf<Person>(
                                    Person(),
                                    Person(),
                                    Person(),
                                    Person()
                                )
                            }

                            var selectedPerson by remember { mutableStateOf<Person?>(null) }


                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                AnimatedContent(
                                    targetState = selectedPerson,
                                    transitionSpec = {
                                        fadeIn(animationSpec = tween(300)) + slideInHorizontally() togetherWith
                                                fadeOut(animationSpec = tween(300)) + slideOutHorizontally()
                                    }
                                ) { item ->
                                    if (item != null) {
                                        PersonDetails(
                                            person = item,
                                            onDismiss = { selectedPerson = null }
                                        )
                                    } else {
                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            items(people, key = { it.id }) { person ->
                                                Box(modifier = Modifier.clickable {
                                                    selectedPerson = person
                                                }) {
                                                    Text(text = person.name)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonDetails(person: Person, onDismiss: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { expanded = true }
    Box(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize()
            .background(Color.LightGray)
            .clickable { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = person.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(visible = expanded) {
                Column {
                    Text(text = "Phone ${person.phone}")
                    Text(text = "Email ${person.email}")
                    Text(text = "Age ${person.age}")
                }
            }
        }
    }
}

data class Person @OptIn(ExperimentalUuidApi::class) constructor(
    var id: Uuid = Uuid.random(),
    var name: String = id.toString(),
    var age: Int = id.hashCode(),
    var email: String = id.toString(),
    var phone: Int = id.hashCode()
)