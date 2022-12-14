package com.borshevskiy.fkn_labs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.presentation.MainViewModel
import com.borshevskiy.fkn_labs.presentation.screens.DetailScreen
import com.borshevskiy.fkn_labs.presentation.screens.MainScreen
import dev.chrisbanes.snapper.ExperimentalSnapperApi

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = MAIN_SCREEN)
    object DetailScreen: Screen(route = DETAIL_SCREEN)

    companion object {
        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
    }
}

@ExperimentalSnapperApi
@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) { MainScreen(navController, viewModel) }
        composable(route = Screen.DetailScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<MarvelHero>("marvelHero")?.let {
                DetailScreen(it, navController)
            }
        }
    }
}