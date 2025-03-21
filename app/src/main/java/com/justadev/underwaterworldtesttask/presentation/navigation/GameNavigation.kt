package com.justadev.underwaterworldtesttask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.justadev.underwaterworldtesttask.presentation.screen.game.GameScreen
import com.justadev.underwaterworldtesttask.presentation.screen.menu.MenuScreen
import com.justadev.underwaterworldtesttask.presentation.screen.result.ResultScreen
import com.justadev.underwaterworldtesttask.presentation.screen.splash.SplashScreen

@Composable
fun GameNavigation(navController: NavHostController, navigator: Navigator) {

    NavHost(navController = navController, startDestination = Route.Splash) {

        composable<Route.Splash> {
            SplashScreen(navigator)
        }
        composable<Route.Menu> {
            MenuScreen(navigator)
        }
        composable<Route.Game> {
            GameScreen(navigator)
        }
        composable<Route.Result> {
            val route = it.toRoute<Route.Result>()
            ResultScreen(route.isWin, route.score, navigator)
        }

    }

}