package com.baec23.tenaday.ui.comp

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.launch

@Composable
fun AnimatedChar(
    modifier: Modifier = Modifier,
    initialChar: Char,
    char: Char,
    textStyle: TextStyle,
    enterDuration: Int = 200,
    exitDuration: Int = 200
) {
    var currChar by remember { mutableStateOf(initialChar) }
    var animatedRotationY by remember { mutableFloatStateOf(0f) }
    var animatedScale by remember { mutableFloatStateOf(0f) }
    var animatedAlpha by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    suspend fun startAnimation(targetChar: Char) {
        val rotationStart = coroutineScope.launch {
            animate(
                animatedRotationY,
                90f,
                animationSpec = tween(durationMillis = enterDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedRotationY = value
            }
        }
        val scaleStart = coroutineScope.launch {
            animate(
                animatedScale,
                1.5f,
                animationSpec = tween(durationMillis = enterDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedScale = value
            }
        }
        val alphaStart = coroutineScope.launch {
            animate(
                animatedAlpha,
                0.5f,
                animationSpec = tween(durationMillis = enterDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedAlpha = value
            }
        }
        rotationStart.join()
        scaleStart.join()
        alphaStart.join()
        currChar = targetChar
        val rotationEnd = coroutineScope.launch {
            animate(
                animatedRotationY,
                0f,
                animationSpec = tween(durationMillis = exitDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedRotationY = value
            }
        }
        val scaleEnd = coroutineScope.launch {
            animate(
                animatedScale,
                1f,
                animationSpec = tween(durationMillis = exitDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedScale = value
            }
        }
        val alphaEnd = coroutineScope.launch {
            animate(
                animatedAlpha,
                1f,
                animationSpec = tween(durationMillis = exitDuration, easing = FastOutSlowInEasing)
            ) { value, _ ->
                animatedAlpha = value
            }
        }
        rotationEnd.join()
        scaleEnd.join()
        alphaEnd.join()
    }

    LaunchedEffect(key1 = char) {
        if (char != currChar) {
            coroutineScope.launch {
                startAnimation(char)
            }
        }
    }

    Text(
        modifier = modifier.graphicsLayer {
            rotationY = animatedRotationY
            scaleX = animatedScale
        }.alpha(animatedAlpha),
        text = currChar.toString(),
        style = textStyle
    )
}
