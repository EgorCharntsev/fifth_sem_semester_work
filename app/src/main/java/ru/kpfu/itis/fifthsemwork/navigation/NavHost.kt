package ru.kpfu.itis.fifthsemwork.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.kpfu.itis.common.navigation.Route

@Composable
fun NavHostContainer(
    navController: NavHostController,
    navigator: Navigator,
    startDestination: Route,
    onBottomBarVisibilityChanged: (Boolean) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.toString(),
    ) {
        with(navigator.auth) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.home) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.favorites) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
        with(navigator.search) {
            registerGraph(navController, onBottomBarVisibilityChanged)
        }
    }

}