package com.baec23.tenaday.ui.comp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG = "AnimatedChangingText"

@Composable
fun AnimatedText(
    initialText: String,
    text: String,
    textStyle: TextStyle,
    animationDuration: Int = 500
) {
    val currText by remember { mutableStateOf(initialText) }
    var chars by remember { mutableStateOf(initialText.map { char -> char }) }
    val coroutineScope = rememberCoroutineScope()

    suspend fun updateChars(targetText: String) {
        val targetTextChars = targetText.map { c -> c }
        val mutableChars = chars.toMutableList()
        val delayDuration = if (chars.isNotEmpty()) animationDuration / chars.size else 100
        targetTextChars.forEachIndexed { index, c ->
            if (index < mutableChars.size) {
                mutableChars[index] = c
            } else {
                mutableChars.add(c)
            }
            chars = mutableChars.toList()
            delay(delayDuration.toLong())
        }
    }
    LaunchedEffect(key1 = text) {
        if (text != currText) {
            coroutineScope.launch { updateChars(text) }
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        if (chars.isNotEmpty()) {
            repeat(chars.size) {
                AnimatedChar(
                    initialChar = ' ',
                    char = chars[it],
                    textStyle = textStyle
                )
            }
        }
    }
}
