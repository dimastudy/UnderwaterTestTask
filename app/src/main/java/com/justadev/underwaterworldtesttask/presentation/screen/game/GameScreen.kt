package com.justadev.underwaterworldtesttask.presentation.screen.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.core.theme.UnderwaterWorldTestTaskTheme
import com.justadev.underwaterworldtesttask.domain.model.Game
import com.justadev.underwaterworldtesttask.domain.model.GameStatus
import com.justadev.underwaterworldtesttask.presentation.screen.game.components.IdleScreen
import com.justadev.underwaterworldtesttask.presentation.screen.game.components.PlayScreen
import com.justadev.underwaterworldtesttask.core.components.gameBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val SPAWN_DELAY = 3000L

@Composable
fun GameScreen(gameNavigator: GameNavigator) {
    val viewModel: GameViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.game.status) {
        if (state.game.status == GameStatus.Over) {
            gameNavigator.navigateResult(state.isWinGame, state.game.score)
        }
    }

    Content(state, viewModel::onEvent)
}

@Composable
private fun Content(state: GameState, sendEvent: (GameEvent) -> Unit) {

    val scope = rememberCoroutineScope()
    var screenWidth by remember { mutableIntStateOf(0) }
    var screenHeight by remember { mutableIntStateOf(0) }
    val stoneImage = ImageBitmap.imageResource(R.drawable.stone)
    val bonusImage = ImageBitmap.imageResource(R.drawable.bonus)
    val sandClockImage = ImageBitmap.imageResource(R.drawable.sand_clock)

    LaunchedEffect(state.game.status) {
        while (state.game.status == GameStatus.Started) {
            delay(SPAWN_DELAY)
            sendEvent(
                GameEvent.SpawnFallingObject(
                    screenWidth = screenWidth,
                    stoneImage,
                    bonusImage,
                    sandClockImage
                )
            )

        }
    }

    LaunchedEffect(state.game.status) {
        while (state.game.status == GameStatus.Started) {
            withFrameMillis {
                sendEvent(GameEvent.MoveFallingObjects(moveAction = { list ->
                    list.forEach { fallingObject ->
                        scope.launch(Dispatchers.Main) {
                            fallingObject.y.animateTo(
                                targetValue = fallingObject.y.value + fallingObject.fallingSpeed
                            )
                        }
                    }
                }))
                sendEvent(GameEvent.CheckCollision)
            }
        }
    }

    LaunchedEffect(key1 = state.timeLeft, key2 = state.game.status) {
        if (state.timeLeft > 0 && state.game.status == GameStatus.Started) {
            delay(1000L)
            sendEvent(GameEvent.DecreaseTime)
        }
        if (state.timeLeft <= 0) {
            sendEvent(GameEvent.EndGame)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                screenWidth = it.size.width
                screenHeight = it.size.height
                sendEvent(GameEvent.SetupShipPosition(screenWidth, screenHeight))
            }.gameBackground()) {
        when (state.game.status) {
            GameStatus.Idle -> IdleScreen(sendEvent)
            GameStatus.Started -> PlayScreen(state, screenWidth, sendEvent)
            GameStatus.Over -> {}
        }
    }


}


@Preview(showSystemUi = true)
@Composable
private fun GameScreenPreview() {
    UnderwaterWorldTestTaskTheme {
        Content(state = GameState(game = Game(status = GameStatus.Idle)), sendEvent = {})
    }
}


