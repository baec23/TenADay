@file:OptIn(ExperimentalAnimationGraphicsApi::class)

package com.baec23.tenaday.ui.screen.main.test

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField
import com.baec23.ludwig.component.inputfield.InputValidator
import com.baec23.ludwig.morpher.component.AnimatedMorphVector
import com.baec23.tenaday.ui.comp.AnimatedText
import com.baec23.tenaday.ui.comp.BaseScreen

/*
*
* TODO: Add route to NavHost
*
*/
const val testScreenRoute = "test_screen_route"
fun NavGraphBuilder.testScreen() {
    composable(route = testScreenRoute) {
        TestScreen()
    }
}

fun NavController.navigateToTestScreen(navOptions: NavOptions? = null) {
    navigate(route = testScreenRoute, navOptions = navOptions)
}

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel()
) {
//    val animationProgress = remember {
//        Animatable(0f)
//    }
//    var input by remember { mutableStateOf("") }
//    var displayedText by remember { mutableStateOf("") }

    BaseScreen {
        Column(modifier = Modifier.fillMaxSize()) {
//            AnimatedMorphVector(startSource = , endSource = , progress = )
//            InputField(value = input, onValueChange = {
//                input = it
//            }, label = "Input", inputValidator = InputValidator.Any)
//            Spacer(modifier = Modifier.height(16.dp))
//            AnimatedText(
//                initialText = "",
//                text = displayedText,
//                textStyle = MaterialTheme.typography.displaySmall
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            StatefulButton(text = "CHANGE") {
//                displayedText = input
//            }
        }
    }
}
