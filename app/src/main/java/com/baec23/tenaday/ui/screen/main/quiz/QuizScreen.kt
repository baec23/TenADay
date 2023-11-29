package com.baec23.tenaday.ui.screen.main.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.tenaday.ui.comp.BaseScreen
import com.baec23.tenaday.ui.screen.main.quiz.comp.QuizQuestionCard

const val quizScreenRoute = "quiz_screen_route"
fun NavGraphBuilder.quizScreen() {
    composable(
        route = quizScreenRoute
    ) {
        val viewModel = hiltViewModel<QuizViewModel>()
        val uiState by viewModel.uiState.collectAsState()
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
    BaseScreen() {
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
                incorrectAnswerIndexes = uiState.incorrectAnswerIndexes,
                isBusy = uiState.isBusy,
                hasError = uiState.error != null,
                busyContent = { BusyContent() },
                errorContent = {
                    ErrorContent(
                        errorMessage = uiState.error,
                        onErrorRetry = { onEvent(QuizUiEvent.OnRetryPress) }
                    )
                },
                correctAnswerIndex = uiState.correctAnswerIndex,
                hasAnsweredCorrectly = uiState.hasAnsweredCorrectly,
                onAnswerPress = {
                    onEvent(QuizUiEvent.OnAnswerPress(it))
                }
            )
        }
    }
}

@Composable
private fun BusyContent() {
    CircularProgressIndicator(strokeWidth = 6.dp, strokeCap = StrokeCap.Round)
}

@Composable
private fun ErrorContent(
    errorMessage: String?,
    onErrorRetry: () -> Unit
) {
    Text(text = "Error", style = MaterialTheme.typography.displaySmall)
    Spacer(modifier = Modifier.height(8.dp))
    errorMessage?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
    Spacer(modifier = Modifier.height(16.dp))
    StatefulButton(text = "Retry", onClick = { onErrorRetry() })
}
