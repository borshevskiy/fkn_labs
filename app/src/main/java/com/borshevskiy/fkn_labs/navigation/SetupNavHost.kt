package com.borshevskiy.fkn_labs.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.borshevskiy.fkn_labs.screens.DetailScreen
import com.borshevskiy.fkn_labs.screens.MainScreen
import com.borshevskiy.fkn_labs.utils.Constants.Key.HERO_NAME
import com.borshevskiy.fkn_labs.utils.Constants.Screens.DETAIL_SCREEN
import com.borshevskiy.fkn_labs.utils.Constants.Screens.MAIN_SCREEN
import dev.chrisbanes.snapper.ExperimentalSnapperApi

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = MAIN_SCREEN)
    object DetailScreen: Screen(route = DETAIL_SCREEN)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { append("/$it") }
        }
    }
}

@ExperimentalSnapperApi
@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) { MainScreen(navController) }
        composable(route = Screen.DetailScreen.route + "/{$HERO_NAME}",
        arguments = listOf(navArgument(name = HERO_NAME) {
            type = NavType.StringType
        })) {
            DetailScreen(heroName = it.arguments?.getString(HERO_NAME), navController)
        }
    }
}