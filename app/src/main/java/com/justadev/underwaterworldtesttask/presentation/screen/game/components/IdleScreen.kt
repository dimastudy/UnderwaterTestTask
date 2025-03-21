package com.justadev.underwaterworldtesttask.presentation.screen.game.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.justadev.underwaterworldtesttask.R
import com.justadev.underwaterworldtesttask.presentation.screen.game.GameEvent

@Composable
fun IdleScreen(sendEvent: (GameEvent) -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f))
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Moving the submarine is done by swiping the screen left or right.",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Image(
                    painter = painterResource(R.drawable.swipe_left),
                    contentDescription = stringResource(
                        R.string.swipe_left
                    ),
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painter = painterResource(R.drawable.swipe_right),
                    contentDescription = stringResource(
                        R.string.swipe_right
                    ),
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(
                "Each second of playing time gives you +1 score", color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Obstacles:", color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.stone),
                    contentDescription = stringResource(
                        R.string.obstacle
                    ),
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    stringResource(R.string.stone_obstacle_if_ship_collide_with_it_1_life_point_will_be_removed),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.bonus),
                    contentDescription = stringResource(
                        R.string.obstacle
                    ),
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    stringResource(R.string.bonus_obstacle_if_ship_collide_with_it_5_score_will_be_granted),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.sand_clock),
                    contentDescription = stringResource(
                        R.string.obstacle
                    ),
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    stringResource(R.string.sand_clock_obstacle_if_ship_collide_with_it_5_seconds_will_be_granted_to_your_time),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Button(
            onClick = {
                sendEvent(GameEvent.StartGame)
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 32.dp)
        ) {
            Text(stringResource(R.string.start))
        }

    }
}
