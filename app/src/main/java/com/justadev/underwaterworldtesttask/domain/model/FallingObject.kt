package com.justadev.underwaterworldtesttask.domain.model

import androidx.compose.animation.core.Animatable
import androidx.compose.ui.graphics.ImageBitmap

interface FallingObject {
    val x: Float
    val y: Animatable<Float, *>
    val radius: Float
    val fallingSpeed: Float
    val image: ImageBitmap
    val width: Int
    val height: Int
}