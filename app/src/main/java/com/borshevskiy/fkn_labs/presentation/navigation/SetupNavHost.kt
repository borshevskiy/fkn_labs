package com.borshevskiy.fkn_labs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.borshevskiy.fkn_labs.presentation.MainViewModel
import com.borshevskiy.fkn_labs.presentation.navigation.Screen.Companion.FROM_NOTIFICATION
import com.borshevskiy.fkn_labs.presentation.navigation.Screen.Companion.HERO_ID
import com.borshevskiy.fkn_labs.presentation.screens.DetailScreen
import com.borshevskiy.fkn_labs.presentation.screens.MainScreen
import dev.chrisbanes.snapper.ExperimentalSnapperApi

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = MAIN_SCREEN)
    object DetailScreen: Screen(route = "$DETAIL_SCREEN/{$HERO_ID}") {
        fun passArgument(heroId: Int) = "$DETAIL_SCREEN/$heroId"
    }

    companion object {
        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
        const val HERO_ID = "heroId"
        const val FROM_NOTIFICATION = "fromNotification"
    }
}

@ExperimentalSnapperApi
@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) { MainScreen(navController, viewModel) }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(HERO_ID) { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$FROM_NOTIFICATION/$HERO_ID={$HERO_ID}" })
        ) {
            it.arguments?.getInt(HERO_ID)?.let { heroId -> DetailScreen(heroId, navController, viewModel)}
        }
    }
}