package com.baec23.tenaday.ui.screen.main.morphtest

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val morphTestScreenRoute = "morph_test_screen_route"
fun NavGraphBuilder.morphTestScreen() {
    composable(route = morphTestScreenRoute) {
        val viewModel = hiltViewModel<MorphTestViewModel>()
        val uiState = viewModel.uiState
        MorphTestScreen(uiState = uiState, onEvent = viewModel::onEvent)
    }
}

fun NavController.navigateToMorphTestScreen(navOptions: NavOptions? = null) {
    navigate(route = morphTestScreenRoute, navOptions = navOptions)
}

@Composable
private fun MorphTestScreen(
    uiState: MorphTestUiState,
    onEvent: (MorphTestUiEvent) -> Unit
) {

}