package com.justadev.underwaterworldtesttask.presentation.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.core.theme.UnderwaterWorldTestTaskTheme
import com.justadev.underwaterworldtesttask.core.components.defaultBackground
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navigator: SplashNavigator) {
    val viewModel = hiltViewModel<SplashViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.splashEnded) {
        if (state.splashEnded) {
            navigator.afterSplashNavigation()
        }
    }

    Content(viewModel::onEvent)

}

@Composable
private fun Content(sendEvent: (SplashEvent) -> Unit) {

    var screenWidth by remember { mutableIntStateOf(0) }
    var screenHeight by remember { mutableIntStateOf(0) }
    val offsetXShip = remember { Animatable(0f) }
    val offsetYStone1 = remember { Animatable(-200f) }
    val offsetYStone2 = remember { Animatable(-200f) }
    val offsetYBonus = remember { Animatable(-200f) }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                offsetXShip.animateTo(
                    targetValue = 300f,
                    animationSpec = tween(durationMillis = 1000)
                )
            }
            launch {
                offsetYStone1.animateTo(
                    targetValue = screenHeight.toFloat(),
                    animationSpec = tween(durationMillis = 1500)
                )
            }
        }
        coroutineScope {
            launch {
                offsetXShip.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 1000)
                )
            }
            launch {
                offsetYStone2.animateTo(
                    targetValue = screenHeight.toFloat(),
                    animationSpec = tween(durationMillis = 1500)
                )
            }
        }
        coroutineScope {
            launch {
                offsetYBonus.animateTo(
                    targetValue = screenHeight.toFloat() / 2f - 300f,
                    animationSpec = tween(durationMillis = 1500)
                )
            }
        }
        sendEvent(SplashEvent.EndSplash)
    }

    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                screenWidth = it.size.width
                screenHeight = it.size.height
            }
            .defaultBackground()) {

        Image(
            painter = painterResource(R.drawable.ship),
            contentDescription = stringResource(R.string.game_ship),
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    translationX = offsetXShip.value
                }
        )
        Image(
            painter = painterResource(R.drawable.stone),
            contentDescription = stringResource(R.string.game_ship),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    translationY = offsetYStone1.value
                }
        )
        Image(
            painter = painterResource(R.drawable.stone),
            contentDescription = stringResource(R.string.game_ship),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .graphicsLayer {
                    translationX -= 100f
                    translationY = offsetYStone2.value
                }
        )
        Image(
            painter = painterResource(R.drawable.bonus),
            contentDescription = stringResource(R.string.game_ship),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    translationY -= 200
                    translationY = offsetYBonus.value
                }
        )

    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    UnderwaterWorldTestTaskTheme {
        Content({})
    }
}