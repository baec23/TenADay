package com.baec23.tenaday.ui.screen.main.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.tenaday.model.KoreanWord
import com.baec23.tenaday.model.QuizQuestion
import com.baec23.tenaday.repository.QuizQuestionRepository
import com.baec23.tenaday.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizQuestionRepository: QuizQuestionRepository,
    private val snackbarService: SnackbarService
) : ViewModel() {
    var uiState by mutableStateOf(QuizUiState())
        private set
    private var currQuestion: QuizQuestion? = null
    fun onEvent(event: QuizUiEvent) {
        currQuestion?.let {
            when (event) {
                is QuizUiEvent.OnAnswerPress -> {
                    if (currQuestion!!.baseWord.definitions.contains(event.pressedString)) {
                        viewModelScope.launch {
                            snackbarService.showSnackbar("Correct!")
                            delay(1000)
                            loadRandomQuizQuestion()
                        }
                    } else {
                        uiState =
                            uiState.copy(
                                incorrectAnswers = uiState.incorrectAnswers.plus(
                                    event.pressedString
                                )
                            )
                    }
                }
            }
        }
    }

    private fun loadRandomQuizQuestion() {
        viewModelScope.launch {
            uiState = uiState.copy(isBusy = true)
            val result = quizQuestionRepository.getRandomQuizQuestion()
            val quizQuestion = result.getOrElse {
                uiState =
                    uiState.copy(isBusy = false, error = "Something went wrong!\n${it.message}")
                currQuestion = null
                return@launch
            }
            val currQuestionText = quizQuestion.baseWord.text
            val potentialAnswers = quizQuestion.incorrectAnswers.plus(
                KoreanWord(
                    text = quizQuestion.baseWord.definitions.shuffled().first(),
                    partOfSpeech = quizQuestion.baseWord.partOfSpeech,
                    definitions = listOf(quizQuestion.baseWord.text)
                )
            ).shuffled()
            uiState =
                uiState.copy(
                    isBusy = false,
                    error = null,
                    currQuestionText = currQuestionText,
                    potentialAnswers = potentialAnswers,
                    incorrectAnswers = listOf()
                )
            currQuestion = quizQuestion
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
    val incorrectAnswers: List<String> = listOf()
)

sealed class QuizUiEvent {
    data class OnAnswerPress(val pressedString: String) : QuizUiEvent()
}
