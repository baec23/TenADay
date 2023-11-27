package com.baec23.tenaday.ui.screen.main.first

import androidx.lifecycle.ViewModel
import com.baec23.tenaday.model.SampleItem
import com.baec23.tenaday.navigation.NavService
import com.baec23.tenaday.repository.SampleItemRepository
import com.baec23.tenaday.ui.screen.main.firstdetails.navigateToFirstDetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val navService: NavService,
    sampleItemRepository: SampleItemRepository,
) : ViewModel() {

    val allSampleItems = sampleItemRepository.allSampleItems
    fun onEvent(event: FirstUiEvent) {
        when (event) {
            is FirstUiEvent.OnSampleItemSelected -> {
                navService.navigateToFirstDetailsScreen(event.item.id)
            }
        }
    }
}

sealed class FirstUiEvent {
    data class OnSampleItemSelected(val item: SampleItem) : FirstUiEvent()
}
