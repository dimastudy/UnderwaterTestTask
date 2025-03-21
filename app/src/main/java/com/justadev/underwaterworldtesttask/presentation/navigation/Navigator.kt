package com.justadev.underwaterworldtesttask.presentation.navigation

import androidx.navigation.NavHostController
import com.justadev.underwaterworldtesttask.presentation.screen.game.GameNavigator
import com.justadev.underwaterworldtesttask.presentation.screen.menu.MenuNavigator
import com.justadev.underwaterworldtesttask.presentation.screen.result.ResultNavigator
import com.justadev.underwaterworldtesttask.presentation.screen.splash.SplashNavigator

class Navigator(
    private val navController: NavHostController
) : GameNavigator, MenuNavigator, ResultNavigator,
    SplashNavigator {

    override fun navigateResult(isWin: Boolean, score: Int) {
        navController.navigate(Route.Result(isWin, score)) {
            popUpTo(Route.Game) { inclusive = true }
        }
    }

    override fun navigateGame() {
        navController.navigate(Route.Game)
    }

    override fun backToGame(isWin: Boolean, score: Int) {
        navController.navigate(Route.Game) {
            popUpTo(Route.Result(isWin, score)) { inclusive = true }
        }

    }

    override fun navigateMenu() {
        navController.navigate(Route.Menu) {
            popUpTo(Route.Menu) { inclusive = true }
        }
    }

    override fun afterSplashNavigation() {
        navController.navigate(Route.Menu) {
            popUpTo(Route.Splash) { inclusive = true }
        }
    }

}