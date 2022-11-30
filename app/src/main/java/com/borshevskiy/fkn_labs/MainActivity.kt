package com.borshevskiy.fkn_labs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.navigation.compose.rememberNavController
import com.borshevskiy.fkn_labs.navigation.SetupNavHost
import com.borshevskiy.fkn_labs.ui.theme.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@ExperimentalSnapperApi
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fkn_labsTheme {
                SetupNavHost(navController = rememberNavController())
            }
        }
    }
}