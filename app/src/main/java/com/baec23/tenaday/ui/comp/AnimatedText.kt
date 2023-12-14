package com.baec23.tenaday.ui.comp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

const val TAG = "AnimatedChangingText"

@Composable
fun AnimatedText(
    text: String,
    textStyle: TextStyle,
    animationDuration: Int = 1000
) {
//    val coroutineScope = rememberCoroutineScope()
//
//    var currText by remember { mutableStateOf(text) }
//    val currLength = max(currText.length, text.length)
//    val currCharStates by remember { mutableStateListOf(
//        List(currLength){ a -> a}
//    )}
//
//    var currChars by remember { mutableStateOf(initialText.map { c -> c }) }
// //    var currLength by remember { mutableIntStateOf(initialText.length) }
//
//
//    suspend fun updateChars(targetText: String) {
//        numAnimationCompletedChars = 0
//        val delayDuration = if (currLength == 0) 0 else animationDuration / currLength
//
//        val newCharArray = CharArray(currLength) { i ->
//            currChars.getOrNull(i) ?: ' '
//        }
//        val newCharIsAnimating = MutableList(newCharArray.size) { true }
//        charIsAnimating = newCharIsAnimating.toList()
//
//        for (i in newCharArray.indices) {
//            if (i < targetText.length) {
//                newCharArray[i] = targetText[i]
//            } else {
//                newCharArray[i] = ' '
//            }
//            currChars = newCharArray.toList()
//            delay(delayDuration.toLong())
//        }
//    }
//
//    LaunchedEffect(key1 = text) {
//        coroutineScope.launch { updateChars(text) }
//    }
//
//    LaunchedEffect(key1 = numAnimationCompletedChars) {
//        Log.d(
//            TAG,
//            "AnimatedText: numAnimationCompletedChars = $numAnimationCompletedChars - needed = $currLength"
//        )
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        repeat(currLength) { index ->
//            val char = currChars.getOrNull(index) ?: ' '
//            AnimatedChar(
//                modifier = Modifier.border(width = 1.dp, color = Color.Red),
//                initialChar = char,
//                char = char,
//                textStyle = textStyle,
//                onAnimationComplete = {
//                    numAnimationCompletedChars += 1
//                }
//            )
//        }
//    }
}

data class AnimatedCharState(
    val fromChar: Char?,
    val toChar: Char?,
    val color: Color,
    val shouldAnimate: Boolean
)
