package com.baec23.tenaday.ui.screen.main.quiz.comp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baec23.ludwig.component.button.ButtonState
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.tenaday.model.KoreanWord

@Composable
fun QuizQuestionCard(
    modifier: Modifier = Modifier,
    question: String,
    potentialAnswers: List<KoreanWord>,
    correctAnswerIndex: Int,
    hasAnsweredCorrectly: Boolean,
    incorrectAnswerIndexes: List<Int>,
    isBusy: Boolean,
    hasError: Boolean,
    busyContent: @Composable ColumnScope.() -> Unit,
    errorContent: @Composable ColumnScope.() -> Unit,
    onAnswerPress: (Int) -> Unit
) {
    ElevatedCard(elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)) {
        Column(
            modifier = modifier
                .animateContentSize()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (hasError) {
                errorContent()
            } else if (isBusy) {
                busyContent()
            } else {
                Text(text = question, style = MaterialTheme.typography.displaySmall)
                Spacer(modifier = Modifier.height(16.dp))
                potentialAnswers.mapIndexed { index, potentialAnswer ->
                    QuizAnswerButton(
                        displayedWord = potentialAnswer.text,
                        definition = potentialAnswer.definitions.first(),
                        state = if (index == correctAnswerIndex && hasAnsweredCorrectly) {
                            QuizAnswerButtonState.Correct
                        } else if (incorrectAnswerIndexes.contains(
                                index
                            )
                        ) {
                            QuizAnswerButtonState.Incorrect
                        } else {
                            QuizAnswerButtonState.Unanswered
                        }

                    ) {
                        onAnswerPress(index)
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizAnswerButton(
    displayedWord: String,
    definition: String,
    state: QuizAnswerButtonState,
    onPressed: () -> Unit
) {
    StatefulButton(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(40.dp),
        onClick = { onPressed() },
        state = if (state == QuizAnswerButtonState.Unanswered) ButtonState.Enabled else ButtonState.Disabled,
        disabledBorderColor = if (state == QuizAnswerButtonState.Correct) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
    ) {
        Text(
            text = if (state == QuizAnswerButtonState.Unanswered) {
                displayedWord
            } else {
                "$displayedWord: $definition"
            }
        )
    }
}

private enum class QuizAnswerButtonState {
    Unanswered,
    Correct,
    Incorrect
}
