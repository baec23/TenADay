package com.baec23.tenaday.ui.screen.main.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.tenaday.model.KoreanWord
import com.baec23.tenaday.model.QuizQuestion
import com.baec23.tenaday.repository.QuizQuestionRepository
import com.baec23.tenaday.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizQuestionRepository: QuizQuestionRepository,
    private val snackbarService: SnackbarService
) : ViewModel() {
    private var currQuestion = MutableStateFlow<QuizQuestion?>(null)
    private var incorrectAnswerIndexes = MutableStateFlow<List<Int>>(listOf())

    val uiState =
        currQuestion.combine(incorrectAnswerIndexes) { currQuestionData, incorrectAnswerIndexes ->
            if (currQuestionData != null && currQuestionData.correctAnswerIndex == -1) {
                return@combine QuizUiState(
                    isBusy = false,
                    error = "Error fetching question",
                    currQuestionText = "",
                    potentialAnswers = listOf(),
                    correctAnswerIndex = -1,
                    incorrectAnswerIndexes = listOf()
                )
            }
            val isBusy = currQuestionData == null
            val currQuestionText = currQuestionData?.questionString ?: ""
            val potentialAnswers = currQuestionData?.potentialAnswers ?: listOf()
            val correctAnswerIndex = currQuestionData?.correctAnswerIndex ?: -1
            QuizUiState(
                isBusy = isBusy,
                currQuestionText = currQuestionText,
                potentialAnswers = potentialAnswers,
                correctAnswerIndex = correctAnswerIndex,
                incorrectAnswerIndexes = incorrectAnswerIndexes
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = QuizUiState()
        )

    fun onEvent(event: QuizUiEvent) {
        when (event) {
            is QuizUiEvent.OnAnswerPress -> {
                if (currQuestion.value == null) {
                    return
                }
                if (event.pressedIndex == currQuestion.value!!.correctAnswerIndex) {
                    viewModelScope.launch {
                        snackbarService.showSnackbar("Correct!")
                        delay(1000)
                        loadRandomQuizQuestion()
                    }
                } else {
                    incorrectAnswerIndexes.value = incorrectAnswerIndexes.value.plus(
                        event.pressedIndex
                    )
                }
            }

            QuizUiEvent.OnRetryPress -> loadRandomQuizQuestion()
        }
    }

    private fun loadRandomQuizQuestion() {
        incorrectAnswerIndexes.value = listOf()
        currQuestion.value = null
        viewModelScope.launch {
            val result = quizQuestionRepository.getRandomQuizQuestion()
            val quizQuestion = result.getOrElse {
                currQuestion.value = QuizQuestion(
                    questionString = "",
                    potentialAnswers = listOf(),
                    correctAnswerIndex = -1
                )
                return@launch
            }
            currQuestion.value = quizQuestion
        }
    }

    init {
        loadRandomQuizQuestion()
    }
}

data class QuizUiState(
    val isBusy: Boolean = false,
    val error: String? = null,
    val currQuestionText: String = "",
    val potentialAnswers: List<KoreanWord> = listOf(),
    val correctAnswerIndex: Int = -1,
    val incorrectAnswerIndexes: List<Int> = listOf()
)

sealed class QuizUiEvent {
    data class OnAnswerPress(val pressedIndex: Int) : QuizUiEvent()
    data object OnRetryPress : QuizUiEvent()
}
