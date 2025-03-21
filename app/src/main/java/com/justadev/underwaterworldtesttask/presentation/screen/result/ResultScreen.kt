package com.justadev.underwaterworldtesttask.presentation.screen.result

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.core.components.GameButton
import com.justadev.underwaterworldtesttask.core.theme.UnderwaterWorldTestTaskTheme
import com.justadev.underwaterworldtesttask.core.utils.quitGame
import com.justadev.underwaterworldtesttask.core.components.defaultBackground

@Composable
fun ResultScreen(
    isWin: Boolean, score: Int, resultNavigator: ResultNavigator
) {
    val activity = LocalActivity.current

    Content(isWin = isWin,
        score = score,
        goHome = resultNavigator::navigateMenu,
        playAgain = {
            resultNavigator.backToGame(isWin, score)
        },
        quitGame = {
            activity?.quitGame()
        })
}

@Composable
private fun Content(
    isWin: Boolean, score: Int, goHome: () -> Unit, playAgain: () -> Unit, quitGame: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .defaultBackground()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.TopCenter)
        ) {
            Column(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .paint(painter = painterResource(R.drawable.board_2)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    if (isWin) stringResource(R.string.you_won) else stringResource(R.string.you_lost),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    stringResource(R.string.game_score_is, score),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GameButton(R.string.home, onClick = goHome)
                GameButton(R.string.restart, onClick = playAgain)
                GameButton(R.string.quit, onClick = quitGame)
            }
        }
    }
}


@Preview
@Composable
private fun ResultScreenPreview() {
    UnderwaterWorldTestTaskTheme {
        Content(true, 10, {}, {}, {})
    }
}