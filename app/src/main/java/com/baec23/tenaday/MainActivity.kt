package com.baec23.tenaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.baec23.tenaday.ui.App
import com.baec23.tenaday.ui.theme.TenADayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TenADayTheme() {
                App()
            }
        }
    }
}
