package com.baec23.tenaday.ui.screen.main.quiz.comp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    incorrectAnswerIndexes: List<Int>,
    onAnswerPress: (Int) -> Unit
) {
    ElevatedCard(elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)) {
        Column(
            modifier = modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = question, style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))
            potentialAnswers.mapIndexed { index, potentialAnswer ->
                QuizAnswerButton(
                    displayedWord = potentialAnswer.text,
                    definition = potentialAnswer.definitions.first(),
                    isIncorrect = incorrectAnswerIndexes.contains(index)
                ) {
                    onAnswerPress(index)
                }
            }
        }
    }
}

@Composable
private fun QuizAnswerButton(
    displayedWord: String,
    definition: String,
    isIncorrect: Boolean,
    onPressed: () -> Unit
) {
    StatefulButton(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(40.dp),
        onClick = { onPressed() },
        state = if (!isIncorrect) ButtonState.Enabled else ButtonState.Disabled,
        disabledBorderColor = MaterialTheme.colorScheme.error
    ) {
        Text(
            text = if (!isIncorrect) {
                displayedWord
            } else {
                "$displayedWord: $definition"
            }
        )
    }
}
