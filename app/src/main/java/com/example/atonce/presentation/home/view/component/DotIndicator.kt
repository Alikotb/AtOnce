package com.example.atonce.presentation.home.view.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    dotSize: Int = 8,
    dotSpacing: Int = 8,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.onBackground
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(totalDots) {
            val color = if (it == selectedIndex) selectedColor else unSelectedColor
            val animatedSize by animateDpAsState(
                targetValue = if (it == selectedIndex) dotSize.dp else dotSize.dp / 2,
                animationSpec = tween(durationMillis = 300)
            )

            Dot(
                size = animatedSize,
                color = color,
                spacing = dotSpacing.dp
            )
        }

    }
}

@Composable
private fun Dot(
    size: Dp,
    color: Color,
    spacing: Dp
) {
    Box(
      modifier = Modifier
          .padding(horizontal = spacing / 2)
          .size(size)
          .background(color = color, shape = CircleShape)
    )
}