package com.justadev.underwaterworldtesttask.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource


@Composable
fun GameButton(textId: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier.fillMaxWidth(0.5f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF428755))) {
        Text(text = stringResource(textId))
    }
}