package com.justadev.underwaterworldtesttask.core.utils

import android.graphics.RectF
import com.justadev.underwaterworldtesttask.domain.model.FallingObject
import com.justadev.underwaterworldtesttask.domain.model.SHIP_HEIGHT
import com.justadev.underwaterworldtesttask.domain.model.SHIP_WIDTH
import com.justadev.underwaterworldtesttask.domain.model.Ship

fun isCollisionV2(ship: Ship, fallingObject: FallingObject): Boolean {
    val shipRect =
        RectF(ship.x.value, ship.y, ship.x.value + (SHIP_WIDTH * 0.5f), ship.y + SHIP_HEIGHT)
    val fallingObjectRect = RectF(
        fallingObject.x,
        fallingObject.y.value,
        fallingObject.x + fallingObject.width,
        fallingObject.y.value + fallingObject.height
    )
    return shipRect.intersect(fallingObjectRect)
}