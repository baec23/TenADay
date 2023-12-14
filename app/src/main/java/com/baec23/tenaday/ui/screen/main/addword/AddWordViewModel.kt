package com.baec23.tenaday.ui.screen.main.addword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baec23.tenaday.model.PartOfSpeech
import com.baec23.tenaday.repository.WordRepository
import com.baec23.tenaday.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

const val TAG = "AddWordViewModel"

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val wordRepository: WordRepository,
    private val snackbarService: SnackbarService
) : ViewModel() {
    internal var uiState by mutableStateOf(AddWordUiState())
        private set

    internal fun onEvent(event: AddWordUiEvent) {
        when (event) {
            AddWordUiEvent.OnAddWordPress -> {
                viewModelScope.launch {
                    uiState = uiState.copy(isBusy = true)
                    val result = wordRepository.saveWord(
                        baseWord = uiState.baseWordText,
                        partOfSpeech = uiState.selectedPartOfSpeech,
                        definitions = listOf(uiState.definitionText)
                    )
                    val savedWord = result.getOrElse {
                        uiState = uiState.copy(
                            isBusy = false,
                            error = "Something went wrong!\n${it.message}"
                        )
                        return@launch
                    }
                    uiState = uiState.copy(
                        isBusy = false,
                        error = null,
                        baseWordText = "",
                        selectedPartOfSpeech = PartOfSpeech.Noun,
                        definitionText = ""
                    )
                    snackbarService.showSnackbar("Added word: ${savedWord.text}")
                }
            }

            is AddWordUiEvent.OnPartOfSpeechChanged -> {
                uiState = uiState.copy(selectedPartOfSpeech = event.newPos)
            }

            is AddWordUiEvent.OnBaseWordTextChanged -> {
                uiState = uiState.copy(baseWordText = event.newText)
            }

            is AddWordUiEvent.OnDefinitionTextChanged -> {
                uiState = uiState.copy(definitionText = event.newText)
            }
        }
    }
}

internal data class AddWordUiState(
    val isBusy: Boolean = false,
    val error: String? = null,
    val baseWordText: String = "",
    val selectedPartOfSpeech: PartOfSpeech = PartOfSpeech.Noun,
    val definitionText: String = ""
)

internal sealed class AddWordUiEvent {
    data class OnBaseWordTextChanged(val newText: String) : AddWordUiEvent()
    data class OnPartOfSpeechChanged(val newPos: PartOfSpeech) : AddWordUiEvent()
    data class OnDefinitionTextChanged(val newText: String) : AddWordUiEvent()
    data object OnAddWordPress : AddWordUiEvent()
}
