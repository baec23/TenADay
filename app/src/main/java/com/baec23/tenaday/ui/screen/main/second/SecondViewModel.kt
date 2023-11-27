package com.baec23.tenaday.ui.screen.main.second

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor() : ViewModel() {
//    fun onEvent(event: SecondUiEvent) {
//        when (event) {
//        }
//    }
}

sealed class SecondUiEvent {
}