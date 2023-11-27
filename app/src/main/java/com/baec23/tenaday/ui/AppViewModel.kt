package com.baec23.tenaday.ui

import androidx.lifecycle.ViewModel
import com.baec23.tenaday.navigation.NavService
import com.baec23.tenaday.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val navService: NavService,
    val snackbarService: SnackbarService,
) : ViewModel()
