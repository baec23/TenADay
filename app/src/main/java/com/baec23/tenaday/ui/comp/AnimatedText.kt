package com.baec23.tenaday.ui.comp

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max

const val TAG = "AnimatedChangingText"

@Composable
fun AnimatedText(
    text: String,
    textStyle: TextStyle,
    animationDuration: Int = 1000
) {
    val coroutineScope = rememberCoroutineScope()

    var currText by remember { mutableStateOf(text) }
    val currLength = max(currText.length, text.length)
    val currCharStates by remember { mutableStateListOf(
        List(currLength){ a -> a}
    )}

    var currChars by remember { mutableStateOf(initialText.map { c -> c }) }
//    var currLength by remember { mutableIntStateOf(initialText.length) }


    suspend fun updateChars(targetText: String) {
        numAnimationCompletedChars = 0
        val delayDuration = if (currLength == 0) 0 else animationDuration / currLength

        val newCharArray = CharArray(currLength) { i ->
            currChars.getOrNull(i) ?: ' '
        }
        val newCharIsAnimating = MutableList(newCharArray.size) { true }
        charIsAnimating = newCharIsAnimating.toList()

        for (i in newCharArray.indices) {
            if (i < targetText.length) {
                newCharArray[i] = targetText[i]
            } else {
                newCharArray[i] = ' '
            }
            currChars = newCharArray.toList()
            delay(delayDuration.toLong())
        }
    }

    LaunchedEffect(key1 = text) {
        coroutineScope.launch { updateChars(text) }
    }

    LaunchedEffect(key1 = numAnimationCompletedChars) {
        Log.d(
            TAG,
            "AnimatedText: numAnimationCompletedChars = $numAnimationCompletedChars - needed = $currLength"
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(currLength) { index ->
            val char = currChars.getOrNull(index) ?: ' '
            AnimatedChar(
                modifier = Modifier.border(width = 1.dp, color = Color.Red),
                initialChar = char,
                char = char,
                textStyle = textStyle,
                onAnimationComplete = {
                    numAnimationCompletedChars += 1
                }
            )
        }
    }
}

data class AnimatedCharState(
    val fromChar: Char?,
    val toChar: Char?,
    val color: Color,
    val shouldAnimate: Boolean
)
