package com.justadev.underwaterworldtesttask.domain.model

import androidx.compose.animation.core.Animatable

data class Ship(
    val x: Animatable<Float, *> = Animatable(0f),
    val y: Float = 0f,
    val radius: Float = 40f
)

const val SHIP_WIDTH = 117
const val SHIP_HEIGHT = 302
