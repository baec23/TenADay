package com.baec23.tenaday.ui.screen.main.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.tenaday.ui.comp.BaseScreen
import com.baec23.tenaday.ui.screen.main.quiz.comp.QuizQuestionCard

const val quizScreenRoute = "quiz_screen_route"
fun NavGraphBuilder.quizScreen() {
    composable(route = quizScreenRoute) {
        val viewModel = hiltViewModel<QuizViewModel>()
        val uiState = viewModel.uiState
        QuizScreen(uiState = uiState, onEvent = { event -> viewModel.onEvent(event) })
    }
}

fun NavController.navigateToQuizScreen(navOptions: NavOptions? = null) {
    navigate(route = quizScreenRoute, navOptions = navOptions)
}

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    onEvent: (QuizUiEvent) -> Unit
) {
    BaseScreen(isBusy = uiState.isBusy) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            QuizQuestionCard(
                modifier = Modifier.fillMaxWidth(),
                question = uiState.currQuestionText,
                potentialAnswers = uiState.potentialAnswers,
                incorrectAnswers = uiState.incorrectAnswers,
                onAnswerPress = {
                    onEvent(QuizUiEvent.OnAnswerPress(it))
                }
            )
        }
    }
}
