package com.borshevskiy.fkn_labs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.borshevskiy.fkn_labs.presentation.MainViewModel
import com.borshevskiy.fkn_labs.presentation.navigation.Screen.Companion.HERO_ID
import com.borshevskiy.fkn_labs.presentation.screens.DetailScreen
import com.borshevskiy.fkn_labs.presentation.screens.MainScreen
import dev.chrisbanes.snapper.ExperimentalSnapperApi

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = MAIN_SCREEN)
    object DetailScreen: Screen(route = DETAIL_SCREEN)

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { append("/$it") }
        }
    }

    companion object {
        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
        const val HERO_ID = "heroId"
    }
}

@ExperimentalSnapperApi
@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) { MainScreen(navController, viewModel) }
        composable(route = Screen.DetailScreen.route + "/{$HERO_ID}",
        arguments = listOf(navArgument(name = HERO_ID) {
            type = NavType.IntType
        })) {
            DetailScreen(heroId = it.arguments?.getInt(HERO_ID), navController, viewModel)
        }
    }
}