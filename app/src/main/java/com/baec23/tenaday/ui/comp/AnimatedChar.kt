package com.baec23.tenaday.ui.comp

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

private fun defaultEnterAnimationSpec(durationMillis: Int): AnimationSpec<Float> =
    tween(durationMillis = durationMillis)

private fun defaultExitAnimationSpec(durationMillis: Int): AnimationSpec<Float> =
    tween(durationMillis = durationMillis)

sealed class CharAnimation(
    val enterInitial: Float?,
    val exitInitial: Float?,
    val enterTarget: Float,
    val exitTarget: Float,
    val enterDurationMillis: Int,
    val exitDurationMillis: Int,
    val enterAnimationSpec: AnimationSpec<Float>,
    val exitAnimationSpec: AnimationSpec<Float>
) {
    data class ScaleX(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )

    data class ScaleY(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )

    data class RotationX(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )

    data class RotationY(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )

    data class RotationZ(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )

    data class Alpha(
        // Uses current state value if null
        val enterInitialValue: Float? = null,
        // Uses current state value if null
        val exitInitialValue: Float? = null,
        val enterTargetValue: Float,
        val exitTargetValue: Float,
        val enterDurationMillisValue: Int = 100,
        val exitDurationMillisValue: Int = 100,
        val enterAnimationSpecValue: AnimationSpec<Float> = defaultEnterAnimationSpec(
            enterDurationMillisValue
        ),
        val exitAnimationSpecValue: AnimationSpec<Float> = defaultExitAnimationSpec(
            exitDurationMillisValue
        )
    ) : CharAnimation(
        enterInitial = enterInitialValue,
        exitInitial = exitInitialValue,
        enterTarget = enterTargetValue,
        exitTarget = exitTargetValue,
        enterDurationMillis = enterDurationMillisValue,
        exitDurationMillis = exitDurationMillisValue,
        enterAnimationSpec = enterAnimationSpecValue,
        exitAnimationSpec = exitAnimationSpecValue
    )
}

object CharAnimationDefaults {
    fun rotateInOut(enterDurationMillis: Int = 100, exitDurationMillis: Int = 100) =
        listOf<CharAnimation>(
            CharAnimation.RotationY(
                enterTargetValue = 0f,
                exitTargetValue = 90f,
                enterDurationMillisValue = enterDurationMillis,
                exitDurationMillisValue = exitDurationMillis
            )
        )
}

// @Composable
// fun AnimatedChar(
//    modifier: Modifier = Modifier,
//    initialChar: Char,
//    char: Char,
//    onAnimationComplete: () -> Unit = {},
//    textStyle: TextStyle,
//    charAnimations: List<CharAnimation>? = null,
//    color: Color = MaterialTheme.colorScheme.onBackground
// ) {
//    Log.d(TAG, "AnimatedChar: RECOMPOSED: char = $char")
//    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//        Log.d(TAG, "AnimatedChar: Error during animation: ${exception.localizedMessage}")
//    }
//    val coroutineScope = rememberCoroutineScope() + coroutineExceptionHandler
//
//    var currChar by remember { mutableStateOf(initialChar) }
//    val animations = remember(
//        key1 = charAnimations
//    ) { charAnimations ?: CharAnimationDefaults.rotateInOut() }
//    val animatedStates = remember { mutableMapOf<String, MutableState<Float>>() }
//
//    animations.forEach { animation ->
//        val key = animation::class.simpleName ?: return@forEach
//        if (!animatedStates.containsKey(key)) {
//            animatedStates[key] = when (animation) {
//                is CharAnimation.ScaleX -> remember {
//                    mutableFloatStateOf(
//                        animation.enterInitial ?: 1f
//                    )
//                }
//
//                is CharAnimation.ScaleY -> remember {
//                    mutableFloatStateOf(
//                        animation.enterInitial ?: 1f
//                    )
//                }
//
//                is CharAnimation.Alpha -> remember {
//                    mutableFloatStateOf(
//                        animation.enterInitial ?: 1f
//                    )
//                }
//
//                is CharAnimation.RotationX -> remember {
//                    mutableFloatStateOf(animation.enterInitial ?: 0f)
//                }
//
//                is CharAnimation.RotationY -> remember {
//                    mutableFloatStateOf(animation.enterInitial ?: 0f)
//                }
//
//                is CharAnimation.RotationZ -> remember {
//                    mutableFloatStateOf(animation.enterInitial ?: 0f)
//                }
//            }
//        }
//    }
//
//    suspend fun doAnimate(targetChar: Char) {
//        val exitAnimationJobs = mutableListOf<Job>()
//        for (animation in animations) {
//            val key = animation::class.simpleName
//            val job = coroutineScope.launch {
//                val currStateValue = animatedStates[key]
//                val initialValue = currStateValue?.value ?: (animation.exitInitial ?: 0f)
//                animate(
//                    initialValue = initialValue,
//                    targetValue = animation.exitTarget,
//                    animationSpec = animation.exitAnimationSpec
//                ) { value, _ ->
//                    animatedStates[key]?.value = value
//                }
//            }
//            exitAnimationJobs.add(job)
//        }
//        exitAnimationJobs.joinAll()
//
//        currChar = targetChar
//        if (currChar != ' ') {
//            val enterAnimationJobs = mutableListOf<Job>()
//            for (animation in animations) {
//                val key = animation::class.simpleName
//                val job = coroutineScope.launch {
//                    val currStateValue = animatedStates[key]
//                    val initialValue = currStateValue?.value ?: (animation.enterInitial ?: 0f)
//                    animate(
//                        initialValue = initialValue,
//                        targetValue = animation.enterTarget,
//                        animationSpec = animation.enterAnimationSpec
//                    ) { value, _ ->
//                        animatedStates[key]?.value = value
//                    }
//                }
//                enterAnimationJobs.add(job)
//            }
//            enterAnimationJobs.joinAll()
//        }
//        onAnimationComplete()
//    }
//
//    LaunchedEffect(key1 = char) {
//        coroutineScope.launch {
//            doAnimate(char)
//        }
//    }
//    if (char != 'A') {
//        Column(
//            modifier = modifier.animateContentSize()
//        ) {
//            Text(
//                modifier = Modifier
//                    .graphicsLayer {
//                        scaleX = animatedStates["ScaleX"]?.value ?: 1f
//                        scaleY = animatedStates["ScaleY"]?.value ?: 1f
//                        rotationX = animatedStates["RotationX"]?.value ?: 0f
//                        rotationY = animatedStates["RotationY"]?.value ?: 0f
//                        rotationZ = animatedStates["RotationZ"]?.value ?: 0f
//                    }
//                    .alpha(animatedStates["Alpha"]?.value ?: 1f),
//                text = currChar.toString(),
//                style = textStyle,
//                color = color
//            )
//        }
//    }
// }

@Composable
fun AnimatedChar(
    modifier: Modifier = Modifier,
    fromChar: Char?,
    toChar: Char?,
    onAnimationComplete: () -> Unit = {},
    textStyle: TextStyle,
    charAnimations: List<CharAnimation>? = null,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "AnimatedChar: Error during animation: ${exception.localizedMessage}")
    }
    val coroutineScope = rememberCoroutineScope() + coroutineExceptionHandler

    var currChar by remember { mutableStateOf<Char?>(fromChar) }

    val animations = remember(
        key1 = charAnimations
    ) { charAnimations ?: CharAnimationDefaults.rotateInOut() }
    val animatedPropertyValues = remember { mutableMapOf<String, MutableState<Float>>() }
    animations.forEach { animation ->
        val key = animation::class.simpleName ?: return@forEach
        if (!animatedPropertyValues.containsKey(key)) {
            animatedPropertyValues[key] = when (animation) {
                is CharAnimation.ScaleX -> remember {
                    mutableFloatStateOf(
                        animation.enterInitial ?: 1f
                    )
                }

                is CharAnimation.ScaleY -> remember {
                    mutableFloatStateOf(
                        animation.enterInitial ?: 1f
                    )
                }

                is CharAnimation.Alpha -> remember {
                    mutableFloatStateOf(
                        animation.enterInitial ?: 1f
                    )
                }

                is CharAnimation.RotationX -> remember {
                    mutableFloatStateOf(animation.enterInitial ?: 0f)
                }

                is CharAnimation.RotationY -> remember {
                    mutableFloatStateOf(animation.enterInitial ?: 0f)
                }

                is CharAnimation.RotationZ -> remember {
                    mutableFloatStateOf(animation.enterInitial ?: 0f)
                }
            }
        }
    }

    suspend fun animateEnter() {
        val enterAnimationJobs = mutableListOf<Job>()
        for (animation in animations) {
            val key = animation::class.simpleName
            val job = coroutineScope.launch {
                val currStateValue = animatedPropertyValues[key]
                val initialValue = currStateValue?.value ?: (animation.enterInitial ?: 0f)
                animate(
                    initialValue = initialValue,
                    targetValue = animation.enterTarget,
                    animationSpec = animation.enterAnimationSpec
                ) { value, _ ->
                    animatedPropertyValues[key]?.value = value
                }
            }
            enterAnimationJobs.add(job)
        }
        enterAnimationJobs.joinAll()
    }

    suspend fun animateExit() {
        val exitAnimationJobs = mutableListOf<Job>()
        for (animation in animations) {
            val key = animation::class.simpleName
            val job = coroutineScope.launch {
                val currStateValue = animatedPropertyValues[key]
                val initialValue = currStateValue?.value ?: (animation.exitInitial ?: 0f)
                animate(
                    initialValue = initialValue,
                    targetValue = animation.exitTarget,
                    animationSpec = animation.exitAnimationSpec
                ) { value, _ ->
                    animatedPropertyValues[key]?.value = value
                }
            }
            exitAnimationJobs.add(job)
        }
        exitAnimationJobs.joinAll()
    }

    suspend fun doAnimate() {
        if (fromChar != null) {
            animateExit()
        }
        currChar = toChar
        if (currChar != null && currChar != ' ') {
            animateEnter()
        }
        onAnimationComplete()
    }

    LaunchedEffect(key1 = true) {
        doAnimate()
    }

    if (currChar != null) {
        Column(
            modifier = modifier.animateContentSize()
        ) {
            Text(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = animatedPropertyValues["ScaleX"]?.value ?: 1f
                        scaleY = animatedPropertyValues["ScaleY"]?.value ?: 1f
                        rotationX = animatedPropertyValues["RotationX"]?.value ?: 0f
                        rotationY = animatedPropertyValues["RotationY"]?.value ?: 0f
                        rotationZ = animatedPropertyValues["RotationZ"]?.value ?: 0f
                    }
                    .alpha(animatedPropertyValues["Alpha"]?.value ?: 1f),
                text = currChar.toString(),
                style = textStyle,
                color = color
            )
        }
    }
}
