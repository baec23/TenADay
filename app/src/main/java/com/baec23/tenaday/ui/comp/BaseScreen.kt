package com.baec23.tenaday.ui.comp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    isBusy: Boolean = false,
    content: @Composable () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = isBusy) {
        if (isBusy) {
            keyboardController?.hide()
        }
    }
    Box(modifier = modifier.fillMaxSize()) {
        content()
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = isBusy,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    .zIndex(10f)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
