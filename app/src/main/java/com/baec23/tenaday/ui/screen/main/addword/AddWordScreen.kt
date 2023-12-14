package com.baec23.tenaday.ui.screen.main.addword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.inputfield.InputField
import com.baec23.ludwig.component.section.DisplaySection
import com.baec23.tenaday.model.PartOfSpeech
import com.baec23.tenaday.ui.comp.BaseScreen

const val addWordScreenRoute = "addword_screen_route"
fun NavGraphBuilder.addWordScreen() {
    composable(
        route = addWordScreenRoute
    ) {
        val viewModel = hiltViewModel<AddWordViewModel>()
        val uiState = viewModel.uiState
        AddWordScreen(uiState = uiState, onEvent = viewModel::onEvent)
    }
}

fun NavController.navigateToAddWordScreen(navOptions: NavOptions? = null) {
    navigate(route = addWordScreenRoute, navOptions = navOptions)
}

@Composable
private fun AddWordScreen(
    uiState: AddWordUiState,
    onEvent: (AddWordUiEvent) -> Unit
) {
    val dropdownItems = PartOfSpeech.entries
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedDropdownIndex by remember { mutableIntStateOf(0) }
    BaseScreen(isBusy = uiState.isBusy) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DisplaySection(headerText = "Add Word") {
                InputField(
                    value = uiState.baseWordText,
                    onValueChange = { onEvent(AddWordUiEvent.OnBaseWordTextChanged(newText = it)) },
                    label = "Base English Word",
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = "English Word"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.wrapContentSize()) {
                    Text(
                        modifier = Modifier.clickable { isDropdownExpanded = true },
                        text = dropdownItems[selectedDropdownIndex].name
                    )
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = {
                            isDropdownExpanded = false
                        }
                    ) {
                        dropdownItems.forEachIndexed { index, partOfSpeech ->
                            DropdownMenuItem(
                                text = { Text(partOfSpeech.name) },
                                onClick = {
                                    selectedDropdownIndex = index
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                InputField(
                    value = uiState.definitionText,
                    onValueChange = {
                        onEvent(AddWordUiEvent.OnDefinitionTextChanged(newText = it))
                    },
                    label = "Korean Definition",
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = "Korean definition"

                )
                Spacer(modifier = Modifier.height(16.dp))
                StatefulButton(text = "Save Word") {
                    onEvent(AddWordUiEvent.OnAddWordPress)
                }
            }
        }
    }
}

@Preview
@Composable
fun AddWordScreenPreview() {
    val uiState: AddWordUiState = AddWordUiState(
        isBusy = false,
        error = null,
        baseWordText = "Abcd",
        selectedPartOfSpeech = PartOfSpeech.Adverb,
        definitionText = "Hello"
    )
    AddWordScreen(uiState = uiState, onEvent = {})
}
