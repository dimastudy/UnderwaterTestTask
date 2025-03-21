package com.justadev.underwaterworldtesttask.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Menu : Route

    @Serializable
    data object Game : Route

    @Serializable
    data class Result(val isWin: Boolean, val score: Int) : Route

}