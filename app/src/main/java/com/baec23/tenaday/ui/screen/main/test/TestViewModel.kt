package com.baec23.tenaday.ui.screen.main.test

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.PathParser
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {

    val tentaclePath = PathParser().parsePathString(
        "M226.31 258.64c.77 8.68 2.71 16.48 1.55 25 .15-.78 8.24-5 15.18-7.37 23-3.1 10.84-4.65 22.55 1.17 32.52 4.65 7.37 7.75 11.71 5.81 21.25-2.33 8.67-7.37 16.91-2.71 26 4.26 8.68 7.75 4.34 8.14-3 .39-12.14 0-24.28.77-36 .78-16.91-12-27.75-2.71-44.23 7-12.15 11.24-33 7.76-46.83z"
    ).toNodes()
    val homeIconVectorGroup = Icons.Default.Home.root

    fun onEvent(event: TestUiEvent) {
        when (event) {
            TestUiEvent.OnButtonPress -> {
            }
        }
    }
}

sealed class TestUiEvent {
    data object OnButtonPress : TestUiEvent()
}
