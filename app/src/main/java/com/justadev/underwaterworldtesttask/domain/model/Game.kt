package com.justadev.underwaterworldtesttask.domain.model

data class Game(
    val status: GameStatus = GameStatus.Idle,
    val score: Int = 0,
    val settings: GameSettings = GameSettings()
)

