package com.justadev.underwaterworldtesttask.presentation.screen.result

interface ResultNavigator {
    fun backToGame(isWin: Boolean, score: Int)
    fun navigateMenu()
}