package com.justadev.underwaterworldtesttask.presentation.screen.game.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.core.utils.detectMoveGesture
import com.justadev.underwaterworldtesttask.domain.model.GameStatus
import com.justadev.underwaterworldtesttask.domain.model.SHIP_WIDTH
import com.justadev.underwaterworldtesttask.presentation.screen.game.GameEvent
import com.justadev.underwaterworldtesttask.presentation.screen.game.GameState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PlayScreen(state: GameState, screenWidth: Int, sendEvent: (GameEvent) -> Unit) {

    val scope = rememberCoroutineScope()
    val shipImage = ImageBitmap.imageResource(R.drawable.ship)

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            awaitPointerEventScope {
                detectMoveGesture(gameStatus = state.game.status, onLeft = {
                    sendEvent(GameEvent.MoveShip(moveAction = { ship ->
                        scope.launch(Dispatchers.Main) {
                            ship.x.animateTo(
                                targetValue = if ((ship.x.value - state.game.settings.shipSpeed) >= 0) ship.x.value - state.game.settings.shipSpeed else ship.x.value,
                                animationSpec = tween(30)
                            )
                        }
                    }))
                }, onRight = {
                    sendEvent(GameEvent.MoveShip(moveAction = { ship ->
                        scope.launch(Dispatchers.Main) {
                            ship.x.animateTo(
                                targetValue = if ((ship.x.value + state.game.settings.shipSpeed + SHIP_WIDTH) <= screenWidth) ship.x.value + state.game.settings.shipSpeed else ship.x.value,
                                animationSpec = tween(30)
                            )
                        }
                    }))
                }, onFingerLifted = {})
            }
        }) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            state.fallingObjects.forEach { fallingObject ->
                drawImage(
                    image = fallingObject.image, dstOffset = IntOffset(
                        x = fallingObject.x.toInt(), y = fallingObject.y.value.toInt()
                    )
                )
            }
            drawImage(
                shipImage, dstOffset = IntOffset(
                    x = state.ship.x.value.toInt(), y = state.ship.y.toInt()
                )
            )

        }

        if (state.game.status == GameStatus.Started) {
            Box(
                Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .paint(
                        painter = painterResource(R.drawable.board),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        Modifier
                            .padding(horizontal = 8.dp)
                    ) {
                        repeat(state.lives) {
                            Image(
                                painter = painterResource(R.drawable.heart),
                                contentDescription = stringResource(
                                    R.string.lives_image
                                ),
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.score_is, state.game.score),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.time_left, state.timeLeft),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.Black, fontWeight = FontWeight.Bold
                    )

                }

            }
        }

        AnimatedVisibility(
            visible = state.scoreBonus,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                stringResource(R.string.bonus_score_5),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        AnimatedVisibility(
            visible = state.timeBonus,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                stringResource(R.string.bonus_time_5s),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }


    }
}