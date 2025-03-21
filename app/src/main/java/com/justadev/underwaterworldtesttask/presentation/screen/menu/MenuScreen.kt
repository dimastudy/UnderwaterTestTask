package com.justadev.underwaterworldtesttask.presentation.screen.menu

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.core.components.GameButton
import com.justadev.underwaterworldtesttask.core.theme.UnderwaterWorldTestTaskTheme
import com.justadev.underwaterworldtesttask.core.utils.quitGame
import com.justadev.underwaterworldtesttask.core.components.defaultBackground

@Composable
fun MenuScreen(navigator: MenuNavigator) {

    val activity = LocalActivity.current

    Content(navigateGame = navigator::navigateGame, quitGame = {
        activity?.quitGame()
    })
}

@Composable
private fun Content(navigateGame: () -> Unit, quitGame: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .defaultBackground()
    ) {
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GameButton(R.string.play, onClick = navigateGame)
            GameButton(R.string.quit, onClick = quitGame)
        }
    }
}


@Preview
@Composable
private fun MenuScreenPreview() {
    UnderwaterWorldTestTaskTheme {
        Content({}) { }
    }
}