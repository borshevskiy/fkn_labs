package com.borshevskiy.fkn_labs.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer() {

    val translateAnim = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    ShimmerGridItem(brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        ),
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)))
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .fillMaxHeight()
            .width(400.dp)
            .padding(40.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize().background(brush))
        }
    }
}