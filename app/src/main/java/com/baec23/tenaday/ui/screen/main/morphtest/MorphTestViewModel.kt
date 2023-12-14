package com.baec23.tenaday.ui.screen.main.morphtest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.baec23.ludwig.morpher.model.morpher.VectorSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MorphTestViewModel @Inject constructor() : ViewModel() {
    private val selectableIcons = listOf<ImageVector>(
        Icons.Outlined.Refresh,
        Icons.Outlined.Face,
        Icons.Outlined.FavoriteBorder,
        Icons.Outlined.Home,
        Icons.Outlined.AccountBox,
        Icons.Outlined.AddCircle,
        Icons.Outlined.Check,
        Icons.Outlined.Call,
        Icons.Outlined.ShoppingCart,
        Icons.Outlined.Notifications,
        Icons.Outlined.Lock,
        Icons.Outlined.ThumbUp,
    )
    internal var uiState by mutableStateOf(MorphTestUiState(selectableIcons = selectableIcons))
        private set

    fun onEvent(event: MorphTestUiEvent) {
        when (event) {
            is MorphTestUiEvent.OnIconPress -> {
                val newVectorSource = VectorSource.fromImageVector(selectableIcons[event.index])
                if (uiState.currStartSource == null) {
                    uiState = uiState.copy(
                        currStartSource = newVectorSource,
                        selectedIconIndex = event.index
                    )
                    return
                }
                val oldEndVectorSource = uiState.currEndSource
                if (oldEndVectorSource == null) {
                    uiState = uiState.copy(
                        currEndSource = newVectorSource,
                        selectedIconIndex = event.index
                    )
                    return
                }
                uiState = uiState.copy(
                    currStartSource = oldEndVectorSource,
                    currEndSource = newVectorSource,
                    selectedIconIndex = event.index
                )
            }
        }
    }
}

internal data class MorphTestUiState(
    val currStartSource: VectorSource? = null,
    val currEndSource: VectorSource? = null,
    val selectableIcons: List<ImageVector> = listOf(),
    val selectedIconIndex: Int = 0
)

sealed class MorphTestUiEvent {
    data class OnIconPress(val index: Int) : MorphTestUiEvent()
}

