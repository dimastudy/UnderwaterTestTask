package com.justadev.underwaterworldtesttask.domain.model

import androidx.compose.animation.core.Animatable
import androidx.compose.ui.graphics.ImageBitmap

class FallingScoreBonus(
    override val x: Float,
    override val y: Animatable<Float, *>,
    override val radius: Float,
    override val fallingSpeed: Float,
    override val image: ImageBitmap
) : FallingObject {

    companion object {
        const val WIDTH = 131
        const val HEIGHT = 131
    }

    override val width: Int
        get() = WIDTH
    override val height: Int
        get() = HEIGHT
}