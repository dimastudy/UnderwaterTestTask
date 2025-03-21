package com.justadev.underwaterworldtesttask.presentation.screen.game

import androidx.compose.ui.graphics.ImageBitmap
import com.justadev.underwaterworldtesttask.domain.model.FallingObject
import com.justadev.underwaterworldtesttask.domain.model.Ship

sealed class GameEvent {

    data class SpawnFallingObject(
        val screenWidth: Int,
        val stoneImage: ImageBitmap,
        val bonusImage: ImageBitmap,
        val sandClockImage: ImageBitmap
    ) : GameEvent()

    data class MoveFallingObjects(val moveAction: (List<FallingObject>) -> Unit) : GameEvent()
    data class SetupShipPosition(val screenWidth: Int, val screenHeight: Int) : GameEvent()
    data class MoveShip(val moveAction: (ship: Ship) -> Unit) : GameEvent()
    data object CheckCollision : GameEvent()
    data object StartGame : GameEvent()
    data object DecreaseTime : GameEvent()
    data object EndGame : GameEvent()


}