package com.rperez.animationprac

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rperez.animationprac.presentation.composables.ColorSwitcher
import com.rperez.animationprac.presentation.composables.CrossfadeDemo
import com.rperez.animationprac.presentation.composables.FadingBox
import com.rperez.animationprac.presentation.composables.FourWayFlyOut
import com.rperez.animationprac.presentation.composables.LevitationEffect
import com.rperez.animationprac.presentation.composables.PeopleWithDetails
import com.rperez.animationprac.presentation.composables.SimonGame
import com.rperez.animationprac.presentation.composables.SlideFadeAnimation
import com.rperez.animationprac.presentation.composables.Snowfall
import com.rperez.animationprac.presentation.composables.SudokuGame
import com.rperez.animationprac.ui.theme.AnimationPracTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ConfigurationScreenWidthHeight")
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
                        item(key = 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height.dp)
                            ) {
                                SudokuGame()
                            }
                        }
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
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                PeopleWithDetails()
                            }
                        }
                        item(key = 7) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                FourWayFlyOut()
                            }
                        }
                        item(key = 8) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ColorSwitcher()
                            }
                        }
                        item(key = 9) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(height.dp)
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                SimonGame()
                            }
                        }
                    }
                }
            }
        }
    }
}