package com.justadev.underwaterworldtesttask.presentation.screen.game

import com.justadev.underwaterworldtesttask.domain.model.FallingObject
import com.justadev.underwaterworldtesttask.domain.model.Game
import com.justadev.underwaterworldtesttask.domain.model.Ship

data class GameState(
    val scoreBonus: Boolean = false,
    val timeBonus: Boolean = false,
    val timeLeft: Int = 100,
    val lives: Int = 3,
    val ship: Ship = Ship(),
    val game: Game = Game(),
    val fallingObjects: List<FallingObject> = listOf(),
    val isWinGame: Boolean = false
)
