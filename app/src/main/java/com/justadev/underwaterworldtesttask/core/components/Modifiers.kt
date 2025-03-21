package com.justadev.underwaterworldtesttask.core.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.justadev.underwaterworldtesttask.R

@Composable
fun Modifier.defaultBackground(): Modifier {
    return this
        .paint(
            painter = painterResource(R.drawable.bg_game), contentScale = ContentScale.Crop
        )
        .background(Color.Black.copy(0.5f))
}

@Composable
fun Modifier.gameBackground(): Modifier {
    return this.paint(
            painter = painterResource(R.drawable.bg_game),
            contentScale = ContentScale.Crop
        )
}