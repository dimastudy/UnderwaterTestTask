package com.justadev.underwaterworldtesttask.presentation.screen.game

interface GameNavigator {
    fun navigateResult(isWin: Boolean, score: Int)
}